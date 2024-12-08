import React from 'react';
import {
  Box,
  Text,
  Table,
  Thead,
  Tbody,
  Tr,
  Th,
  Td,
  TableContainer,
} from '@chakra-ui/react';

// Definindo o tipo para as alocações
type Allocation = {
  id: number;
  sala: string;
  data: string;
  horarioInicio: string;
  horarioFim: string;
};

// Dados de exemplo
const alocacoes: Allocation[] = [
  { id: 1, sala: 'Sala G1-01', data: '10/01/2024 - 02/06/2024', horarioInicio: '08:00', horarioFim: '10:00' },
  { id: 2, sala: 'Sala C2-30', data: '13/12/2024 - 02/06/2024', horarioInicio: '10:00', horarioFim: '12:00' },
  { id: 2, sala: 'Sala D0-30', data: '14/12/2024 - 10/07/2024', horarioInicio: '10:00', horarioFim: '12:00' },
  { id: 2, sala: 'Sala D1-24', data: '10/12/2024 - 10/06/2024', horarioInicio: '16:00', horarioFim: '18:00' },
  { id: 2, sala: 'Sala D5-26', data: '12/12/2024 - 18/07/2024', horarioInicio: '10:00', horarioFim: '12:00' },
  { id: 2, sala: 'Sala G1-02', data: '08/12/2024 - 13/12/2024', horarioInicio: '16:00', horarioFim: '18:00' },
  { id: 2, sala: 'Sala D0-12', data: '10/12/2024 - 10/06/2024', horarioInicio: '10:00', horarioFim: '12:00' },
  { id: 2, sala: 'Sala D4-23', data: '08/12/2024 - 12/07/2024', horarioInicio: '10:00', horarioFim: '12:00' },
  { id: 2, sala: 'Sala C1-33', data: '08/12/2024 - 20/06/2024', horarioInicio: '08:00', horarioFim: '10:00' },
  { id: 2, sala: 'Sala C1-02', data: '10/12/2024 - 10/06/2024', horarioInicio: '08:00', horarioFim: '10:00' },
  { id: 2, sala: 'Sala C1-03', data: '10/01/2024 - 02/12/2024', horarioInicio: '07:00', horarioFim: '09:00' },
  { id: 2, sala: 'Sala C2-12', data: '08/01/2024 - 02/12/2024', horarioInicio: '09:00', horarioFim: '11:00' },
  { id: 2, sala: 'Sala C1-09', data: '08/02/2024 - 20/12/2024', horarioInicio: '15:00', horarioFim: '17:00' },
  // Adicione mais alocações conforme necessário
];

const AllocationList = () => {
  return (
    <Box w="90%" margin="auto" mt={6}>
      <Text fontSize="2xl" mb={4} fontWeight='bold'>Alocações</Text>
      {alocacoes.length > 0 ? (
        <TableContainer border="1px" borderColor="gray.200" borderRadius="md">
          <Table variant="simple">
            <Thead bg="#04044c">
              <Tr>
                <Th color="white">Sala</Th>
                <Th color="white">Data</Th>
                <Th color="white">Horário</Th>
              </Tr>
            </Thead>
            <Tbody>
              {alocacoes.map((alocacao) => (
                <Tr key={alocacao.id}>
                  <Td>{alocacao.sala}</Td>
                  <Td>{alocacao.data}</Td>
                  <Td>
                    {alocacao.horarioInicio} - {alocacao.horarioFim}
                  </Td>
                </Tr>
              ))}
            </Tbody>
          </Table>
        </TableContainer>
      ) : (
        <Box textAlign="center" mt={10} fontSize="lg" fontWeight="bold">
          Nenhuma alocação encontrada.
        </Box>
      )}
    </Box>
  );
};

export default AllocationList;



