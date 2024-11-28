import React from 'react';
import {
  Box,
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
  { id: 1, sala: 'Sala 101', data: '12/12/2024', horarioInicio: '08:00', horarioFim: '10:00' },
  { id: 2, sala: 'Sala 102', data: '13/12/2024', horarioInicio: '10:00', horarioFim: '12:00' },
  // Adicione mais alocações conforme necessário
];

const AllocationList = () => {
  return (
    <Box w="90%" margin="auto" mt={6}>
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



