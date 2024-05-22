package com.palco.palcoprontoespacolist;

import com.palco.palcoprontoespacolist.boundary.EspacoRequestDto;
import com.palco.palcoprontoespacolist.boundary.EspacoResponseDto;
import com.palco.palcoprontoespacolist.control.EspacoRepository;
import com.palco.palcoprontoespacolist.control.EspacoService;
import com.palco.palcoprontoespacolist.entity.Espaco;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@SpringBootTest(classes = PalcoProntoApplication.class)
public class PalcoProntoApplicationTests {

	@MockBean
	private EspacoRepository espacoRepository;

	@Autowired
	private EspacoService espacoService;

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

		Mockito.when(espacoRepository.save(Mockito.any(Espaco.class))).thenReturn(palco);

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

		Mockito.when(espacoRepository.findAll()).thenReturn(Arrays.asList(espaco1, espaco2));

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

		Mockito.verify(espacoRepository, Mockito.times(1)).deleteById(id);
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

		Mockito.when(espacoRepository.findById(id)).thenReturn(Optional.of(palcoExistente));

		Espaco palcoEditado = new Espaco();
		palcoEditado.setId(id);
		palcoEditado.setName(requestDto.getName());
		palcoEditado.setDescription(requestDto.getDescription());
		palcoEditado.setDisponibility(requestDto.getDisponibility());
		palcoEditado.setPeopleCapacity(requestDto.getPeopleCapacity());
		palcoEditado.setPlace(requestDto.getPlace());

		Mockito.when(espacoRepository.save(palcoExistente)).thenReturn(palcoEditado);

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

		Mockito.when(espacoRepository.findById(id)).thenReturn(Optional.empty());

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

		Mockito.when(espacoRepository.findById(id)).thenReturn(Optional.of(palcoExistente));

		Espaco palcoEditado = new Espaco();
		palcoEditado.setId(id);
		palcoEditado.setName(requestDto.getName());
		palcoEditado.setDescription(requestDto.getDescription());

		Mockito.when(espacoRepository.save(palcoExistente)).thenReturn(palcoEditado);

		EspacoResponseDto responseDto = espacoService.editarEspaco(id, requestDto);

		assertEquals(id, responseDto.getId());
		assertEquals(requestDto.getName(), responseDto.getName());
		assertEquals(requestDto.getDescription(), responseDto.getDescription());
	}
}





