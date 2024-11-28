// src/components/SalasList.tsx
import React, { useState } from 'react';
import { Box, Button, List, ListItem, Text, useDisclosure } from '@chakra-ui/react';
import { SalaDeAula } from '../../types';
import SalaDetalhes from './ClassroomDetails';

type SalasListProps = {
  salas: SalaDeAula[];
};

const SalasList: React.FC<SalasListProps> = ({ salas }) => {
  const [salaSelecionada, setSalaSelecionada] = useState<SalaDeAula | null>(null);
  const { isOpen, onOpen, onClose } = useDisclosure();

  const handleSalaClick = (sala: SalaDeAula) => {
    setSalaSelecionada(sala);
    onOpen();
  };

  return (
    <Box>
      <Text fontSize="2xl" mb={4}>
        Salas de Aula
      </Text>
      <List spacing={3}>
        {salas.map((sala) => (
          <ListItem key={sala.codigo}>
            <Button variant="link" onClick={() => handleSalaClick(sala)}>
              {sala.codigo} 
            </Button>
          </ListItem>
        ))}
      </List>

      {salaSelecionada && (
        <SalaDetalhes
          sala={salaSelecionada}
          isOpen={isOpen}
          onClose={onClose}
          setSalaSelecionada={setSalaSelecionada}
        />
      )}
    </Box>
  );
};

export default SalasList;
