package com.palco.palcoprontoespacolist;

import com.palco.palcoprontoespacolist.boundary.EspacoRequestDto;
import com.palco.palcoprontoespacolist.boundary.EspacoResponseDto;
import com.palco.palcoprontoespacolist.control.*;
import com.palco.palcoprontoespacolist.entity.Espaco;
import com.palco.palcoprontoespacolist.entity.Eventos;
import com.palco.palcoprontoespacolist.entity.Ingresso;
import com.palco.palcoprontoespacolist.entity.Relatorio;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest(classes = PalcoProntoApplication.class)
public class PalcoProntoApplicationTests {

	@MockBean
	private EspacoRepository espacoRepository;

	@Autowired
	private EspacoService espacoService;


	@MockBean
	private IngressoRepository ingressoRepository;

	@Autowired
	private IngressoService ingressoService;

	@MockBean
	private EventosRepository eventosRepository;

	@Autowired
	private EventosService eventosService;

	@Mock
	private RelatorioRepository relatorioRepository;

	@InjectMocks
	private RelatorioService relatorioService;

	@Test
	public void testSaveEspaco() {
		EspacoRequestDto requestDto = new EspacoRequestDto("PalcoTeste",
				"Descrição", "SãoPaulo", 2000, "Disponivel");
		Espaco palco = new Espaco();
		palco.setName(requestDto.getName());
		palco.setDescription(requestDto.getDescription());
		palco.setPlace(requestDto.getPlace());
		palco.setPeopleCapacity(requestDto.getPeopleCapacity());
		palco.setDisponibility(requestDto.getDisponibility());

		when(espacoRepository.save(Mockito.any(Espaco.class))).thenReturn(palco);

		EspacoResponseDto responseDto = espacoService.saveEspaco(requestDto);

		assertEquals(palco.getId(), responseDto.getId());
		assertEquals(palco.getName(), responseDto.getName());
		assertEquals(palco.getDescription(), responseDto.getDescription());
		assertEquals(palco.getPeopleCapacity(), responseDto.getPeopleCapacity());
	}

	@Test
	public void testGetEspaco() {
		Espaco espaco1 = new Espaco();
		espaco1.setId(1L);
		espaco1.setName("Espaco 1");
		espaco1.setDescription("Descrição 1");
		espaco1.setPeopleCapacity(10);
		espaco1.setDisponibility("Disponivel");

		Espaco espaco2 = new Espaco();
		espaco2.setId(2L);
		espaco2.setName("Espaco 2");
		espaco2.setDescription("Descrição 2");
		espaco2.setPeopleCapacity(20);
		espaco1.setDisponibility("Ocupado");

		when(espacoRepository.findAll()).thenReturn(Arrays.asList(espaco1, espaco2));

		List<EspacoResponseDto> responseDtos = espacoService.getEspaco();

		assertEquals(2, responseDtos.size());
		assertEquals(espaco1.getId(), responseDtos.get(0).getId());
		assertEquals(espaco1.getName(), responseDtos.get(0).getName());
		assertEquals(espaco1.getDescription(), responseDtos.get(0).getDescription());
		assertEquals(espaco1.getPeopleCapacity(), responseDtos.get(0).getPeopleCapacity());
		assertEquals(espaco1.getDisponibility(), responseDtos.get(0).getDisponibility());

		assertEquals(espaco2.getId(), responseDtos.get(1).getId());
		assertEquals(espaco2.getName(), responseDtos.get(1).getName());
		assertEquals(espaco2.getDescription(), responseDtos.get(1).getDescription());
		assertEquals(espaco2.getPeopleCapacity(), responseDtos.get(1).getPeopleCapacity());
		assertEquals(espaco2.getPeopleCapacity(), responseDtos.get(1).getPeopleCapacity());
	}

	@Test
	public void testDeleteEspacoPorId() {
		Long id = 1L;

		espacoService.apagarEspacoPorId(id);

		Mockito.verify(espacoRepository, times(1)).deleteById(id);
	}

	@Test
	public void testEditarEspaco() {
		Long id = 1L;
		EspacoRequestDto requestDto = new EspacoRequestDto("PalcoTest",
				"Nova Descrição", "SãoPaulo", 2000, "Disponivel");

		Espaco palcoExistente = new Espaco();
		palcoExistente.setId(id);
		palcoExistente.setName("PalcoTest Antigo");
		palcoExistente.setDescription("Descrição Antiga");
		palcoExistente.setDisponibility("Disponivel");
		palcoExistente.setPeopleCapacity(10);
		palcoExistente.setPlace("Local Antigo");

		when(espacoRepository.findById(id)).thenReturn(Optional.of(palcoExistente));

		Espaco palcoEditado = new Espaco();
		palcoEditado.setId(id);
		palcoEditado.setName(requestDto.getName());
		palcoEditado.setDescription(requestDto.getDescription());
		palcoEditado.setDisponibility(requestDto.getDisponibility());
		palcoEditado.setPeopleCapacity(requestDto.getPeopleCapacity());
		palcoEditado.setPlace(requestDto.getPlace());

		when(espacoRepository.save(palcoExistente)).thenReturn(palcoEditado);

		EspacoResponseDto responseDto = espacoService.editarEspaco(id, requestDto);

		assertEquals(id, responseDto.getId());
		assertEquals(requestDto.getName(), responseDto.getName());
		assertEquals(requestDto.getDescription(), responseDto.getDescription());
		assertEquals(requestDto.getPeopleCapacity(), responseDto.getPeopleCapacity());
	}

	@Test
	public void testEditarEspacoNaoEncontrado() {
		Long id = 1L;
		EspacoRequestDto requestDto = new EspacoRequestDto("Palco Test",
				"Nova Descrição", "SãoPaulo", 2000, "Disponivel");

		when(espacoRepository.findById(id)).thenReturn(Optional.empty());

		EspacoResponseDto responseDto = espacoService.editarEspaco(id, requestDto);

		assertNull(responseDto);


	}

	@Test
	public void testEditarEspacoParcial() {
		Long id = 1L;
		EspacoRequestDto requestDto = new EspacoRequestDto("PalcoTest",
				"Nova Descrição", "SãoPaulo", 2000, "Sim");

		Espaco palcoExistente = new Espaco();
		palcoExistente.setId(id);
		palcoExistente.setName("Palco Antigo");
		palcoExistente.setDescription("Descrição Antiga");

		when(espacoRepository.findById(id)).thenReturn(Optional.of(palcoExistente));

		Espaco palcoEditado = new Espaco();
		palcoEditado.setId(id);
		palcoEditado.setName(requestDto.getName());
		palcoEditado.setDescription(requestDto.getDescription());

		when(espacoRepository.save(palcoExistente)).thenReturn(palcoEditado);

		EspacoResponseDto responseDto = espacoService.editarEspaco(id, requestDto);

		assertEquals(id, responseDto.getId());
		assertEquals(requestDto.getName(), responseDto.getName());
		assertEquals(requestDto.getDescription(), responseDto.getDescription());
	}

	@Test
	public void testObterTodosIngressos() {
		Ingresso ingresso1 = new Ingresso();
		ingresso1.setId(1L);
		ingresso1.setTipo("Normal");
		ingresso1.setPreco(50.0);
		ingresso1.setQuantidadeDisponivel(200);

		Ingresso ingresso2 = new Ingresso();
		ingresso2.setId(2L);
		ingresso2.setTipo("VIP");
		ingresso2.setPreco(100.0);
		ingresso2.setQuantidadeDisponivel(100);

		when(ingressoRepository.findAll()).thenReturn(Arrays.asList(ingresso1, ingresso2));

		List<Ingresso> ingressos = ingressoService.obterTodosIngressos();

		assertEquals(2, ingressos.size());
		assertEquals(ingresso1.getId(), ingressos.get(0).getId());
		assertEquals(ingresso2.getId(), ingressos.get(1).getId());
	}

	@Test
	public void testObterIngressoPorId() {
		Ingresso ingresso = new Ingresso();
		ingresso.setId(1L);
		ingresso.setTipo("Normal");
		ingresso.setPreco(50.0);
		ingresso.setQuantidadeDisponivel(200);

		when(ingressoRepository.findById(1L)).thenReturn(Optional.of(ingresso));

		Optional<Ingresso> result = ingressoService.obterIngressoPorId(1L);

		assertTrue(result.isPresent());
		assertEquals(ingresso.getId(), result.get().getId());
	}

	@Test
	public void testObterIngressoPorIdNaoEncontrado() {
		when(ingressoRepository.findById(1L)).thenReturn(Optional.empty());

		Optional<Ingresso> result = ingressoService.obterIngressoPorId(1L);

		assertFalse(result.isPresent());
	}

	@Test
	public void testCriarIngresso() {
		Ingresso ingresso = new Ingresso();
		ingresso.setTipo("Normal");
		ingresso.setPreco(50.0);
		ingresso.setQuantidadeDisponivel(200);

		when(ingressoRepository.save(Mockito.any(Ingresso.class))).thenReturn(ingresso);

		Ingresso result = ingressoService.criarIngresso(ingresso);

		assertEquals(ingresso.getTipo(), result.getTipo());
		assertEquals(ingresso.getPreco(), result.getPreco());
		assertEquals(ingresso.getQuantidadeDisponivel(), result.getQuantidadeDisponivel());
	}

	@Test
	public void testAtualizarIngresso() {
		Long id = 1L;
		Ingresso ingressoExistente = new Ingresso();
		ingressoExistente.setId(id);
		ingressoExistente.setTipo("Normal");
		ingressoExistente.setPreco(50.0);
		ingressoExistente.setQuantidadeDisponivel(200);

		Ingresso ingressoAtualizado = new Ingresso();
		ingressoAtualizado.setTipo("VIP");
		ingressoAtualizado.setPreco(100.0);
		ingressoAtualizado.setQuantidadeDisponivel(100);

		when(ingressoRepository.findById(id)).thenReturn(Optional.of(ingressoExistente));
		when(ingressoRepository.save(ingressoExistente)).thenReturn(ingressoExistente);

		Ingresso result = ingressoService.atualizarIngresso(id, ingressoAtualizado);

		assertEquals(id, result.getId());
		assertEquals(ingressoAtualizado.getTipo(), result.getTipo());
		assertEquals(ingressoAtualizado.getPreco(), result.getPreco());
		assertEquals(ingressoAtualizado.getQuantidadeDisponivel(), result.getQuantidadeDisponivel());
	}

	@Test
	public void testAtualizarIngressoNaoExistente() {
		Long id = 1L;
		Ingresso ingressoAtualizado = new Ingresso();
		ingressoAtualizado.setId(id);
		ingressoAtualizado.setTipo("VIP");
		ingressoAtualizado.setPreco(100.0);
		ingressoAtualizado.setQuantidadeDisponivel(100);

		when(ingressoRepository.findById(id)).thenReturn(Optional.empty());
		when(ingressoRepository.save(ingressoAtualizado)).thenReturn(ingressoAtualizado);

		Ingresso result = ingressoService.atualizarIngresso(id, ingressoAtualizado);

		assertEquals(id, result.getId());
		assertEquals(ingressoAtualizado.getTipo(), result.getTipo());
		assertEquals(ingressoAtualizado.getPreco(), result.getPreco());
		assertEquals(ingressoAtualizado.getQuantidadeDisponivel(), result.getQuantidadeDisponivel());
	}

	@Test
	public void testDeletarIngresso() {
		Long id = 1L;

		ingressoService.deletarIngresso(id);

		Mockito.verify(ingressoRepository, times(1)).deleteById(id);
	}
	@Test
	public void testObterTodosEventos() {
		Eventos evento1 = new Eventos();
		evento1.setId(1L);
		evento1.setName("Evento 1");
		evento1.setDescription("Descrição 1");
		evento1.setDatetime(LocalDateTime.now());

		Eventos evento2 = new Eventos();
		evento2.setId(2L);
		evento2.setName("Evento 2");
		evento2.setDescription("Descrição 2");
		evento2.setDatetime(LocalDateTime.now());

		when(eventosRepository.findAll()).thenReturn(Arrays.asList(evento1, evento2));

		List<Eventos> eventos = eventosService.obterTodosEventos();

		assertEquals(2, eventos.size());
		assertEquals(evento1.getId(), eventos.get(0).getId());
		assertEquals(evento2.getId(), eventos.get(1).getId());
	}

	@Test
	public void testObterEventosPorId() {
		Eventos evento = new Eventos();
		evento.setId(1L);
		evento.setName("Evento 1");
		evento.setDescription("Descrição 1");
		evento.setDatetime(LocalDateTime.now());

		when(eventosRepository.findById(1L)).thenReturn(Optional.of(evento));

		Optional<Eventos> result = eventosService.obterEventosPorId(1L);

		assertTrue(result.isPresent());
		assertEquals(evento.getId(), result.get().getId());
	}

	@Test
	public void testObterEventosPorIdNaoEncontrado() {
		when(eventosRepository.findById(1L)).thenReturn(Optional.empty());

		Optional<Eventos> result = eventosService.obterEventosPorId(1L);

		assertFalse(result.isPresent());
	}

	@Test
	public void testCriarEventoComEspacoEIngressos() {
		Espaco espaco = new Espaco();
		espaco.setId(1L);
		espaco.setName("Espaço Teste");

		Ingresso ingresso1 = new Ingresso();
		ingresso1.setId(1L);
		ingresso1.setTipo("Normal");
		ingresso1.setPreco(50.0);

		Ingresso ingresso2 = new Ingresso();
		ingresso2.setId(2L);
		ingresso2.setTipo("VIP");
		ingresso2.setPreco(100.0);

		Eventos evento = new Eventos();
		evento.setId(1L);
		evento.setName("Evento Teste");
		evento.setDescription("Descrição Teste");
		evento.setDatetime(LocalDateTime.now());
		evento.setEspaco(espaco);
		evento.setIngressos(Arrays.asList(ingresso1, ingresso2));

		when(espacoRepository.findById(1L)).thenReturn(Optional.of(espaco));
		when(ingressoRepository.findAllById(Arrays.asList(1L, 2L))).thenReturn(Arrays.asList(ingresso1, ingresso2));
		when(eventosRepository.save(Mockito.any(Eventos.class))).thenReturn(evento);

		Eventos result = eventosService.criarEventoComEspacoEIngressos("Evento Teste", "Descrição Teste", LocalDateTime.now(), 1L, Arrays.asList(1L, 2L));

		assertEquals(evento.getName(), result.getName());
		assertEquals(evento.getDescription(), result.getDescription());
		assertEquals(evento.getEspaco().getId(), result.getEspaco().getId());
		assertEquals(evento.getIngressos().size(), result.getIngressos().size());
	}

	@Test
	public void testDeletarEventos() {
		Long id = 1L;

		eventosService.deletarEventos(id);

		Mockito.verify(eventosRepository, times(1)).deleteById(id);
	}

	@Test
	public void testObterTodosRelatorios() {
		when(relatorioRepository.findAll()).thenReturn(Collections.emptyList());
		assertTrue(relatorioService.obterTodosRelatorios().isEmpty());
	}

	@Test
	public void testObterRelatorioPorId() {
		Long id = 1L;
		Relatorio relatorio = new Relatorio();
		relatorio.setId(id);
		when(relatorioRepository.findById(id)).thenReturn(Optional.of(relatorio));
		assertEquals(relatorio, relatorioService.obterRelatorioPorId(id));
	}

	@Test
	public void testObterRelatorioPorIdNotFound() {
		Long id = 1L;
		when(relatorioRepository.findById(id)).thenReturn(Optional.empty());
		assertThrows(EntityNotFoundException.class, () -> relatorioService.obterRelatorioPorId(id));
	}


	@Test
	public void testAtualizarRelatorio() {
		Long id = 1L;
		Relatorio relatorioExistente = new Relatorio();
		relatorioExistente.setId(id);
		Relatorio relatorioAtualizado = new Relatorio();
		relatorioAtualizado.setId(id);
		when(relatorioRepository.findById(id)).thenReturn(Optional.of(relatorioExistente));
		when(relatorioRepository.save(relatorioExistente)).thenReturn(relatorioAtualizado);
		assertEquals(relatorioAtualizado, relatorioService.atualizarRelatorio(id, relatorioAtualizado));
	}

	@Test
	public void testAtualizarRelatorioNotFound() {
		Long id = 1L;
		when(relatorioRepository.findById(id)).thenReturn(Optional.empty());
		assertThrows(EntityNotFoundException.class, () -> relatorioService.atualizarRelatorio(id, new Relatorio()));
	}

	@Test
	public void testDeletarRelatorio() {
		Long id = 1L;
		doNothing().when(relatorioRepository).deleteById(id);
		relatorioService.deletarRelatorio(id);
		verify(relatorioRepository, times(1)).deleteById(id);
	}
}







