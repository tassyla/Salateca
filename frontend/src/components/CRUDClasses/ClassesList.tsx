// src/components/SalasList.tsx
import React, { useState } from 'react';
import { Box, Button, List, ListItem, Text, useDisclosure } from '@chakra-ui/react';
import { Turma } from '../../types';
import TurmaDetalhes from './ClassesDetails';

type TurmaListProps = {
  turmas: Turma[];
};

const SalasList: React.FC<TurmaListProps> = ({ turmas }) => {
  const [salaSelecionada, setSalaSelecionada] = useState<Turma | null>(null);
  const { isOpen, onOpen, onClose } = useDisclosure();

  const handleTurmaClick = (turma: Turma) => {
    setSalaSelecionada(turma);
    onOpen();
  };

  return (
    <Box margin='20px'>
      <Text fontSize="2xl" mb={4} fontWeight='bold'>
        Turmas
      </Text>
      <List spacing={3}>
        {turmas.map((turma) => (
          <ListItem key={turma.id}>
            <Button variant="link" onClick={() => handleTurmaClick(turma)}>
              {turma.codigo} - {turma.disciplina}
            </Button>
          </ListItem>
        ))}
      </List>

      {salaSelecionada && (
        <TurmaDetalhes
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
