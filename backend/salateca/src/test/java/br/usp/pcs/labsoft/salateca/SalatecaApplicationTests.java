package br.usp.pcs.labsoft.salateca;

import br.usp.pcs.labsoft.salateca.entity.Sala;
import br.usp.pcs.labsoft.salateca.service.GerenciadorDeSala;
import br.usp.pcs.labsoft.salateca.repository.SalaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
class SalatecaApplicationTests {

    @Mock
    private SalaRepository salaRepository;

    @InjectMocks
    private GerenciadorDeSala gerenciadorDeSala;

    @BeforeEach
    void setUp() {
        // Criar salas de teste
        Sala s1 = new Sala("D1-01", 200, true, 0, null, null);
        Sala s2 = new Sala("D1-02", 50, true, 0, null, null);
        Sala s3 = new Sala("D1-03", 100, false, 0, null, null);


        when(salaRepository.findByCodigo("D1-01")).thenReturn(s1);
        when(salaRepository.findByCodigo("D1-02")).thenReturn(s2);
        when(salaRepository.findByCodigo("D1-03")).thenReturn(s3);
    }

    @Test
    void testarBuscarSala() {
        // Teste para buscar as salas pelo código
        Sala sala1 = gerenciadorDeSala.buscarSala("D1-01");
        Sala sala2 = gerenciadorDeSala.buscarSala("D1-02");
        Sala sala3 = gerenciadorDeSala.buscarSala("D1-03");

        // Verificar se as salas retornadas são as esperadas
        assertNotNull(sala1);
        assertEquals("D1-01", sala1.getCodigo());
        assertEquals(200, sala1.getCapacidade());
        
        assertNotNull(sala2);
        assertEquals("D1-02", sala2.getCodigo());
        assertEquals(50, sala2.getCapacidade());

        assertNotNull(sala3);
        assertEquals("D1-03", sala3.getCodigo());
        assertEquals(100, sala3.getCapacidade());
    }
}
