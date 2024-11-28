import React, { useState } from 'react';
import { Box, RadioGroup, Text } from '@chakra-ui/react';
import { StyledHead, StyledBox } from './ClassSelection.styles';
import RadioCard from './components/RadioCard'

const InformacoesTurma = () => {
  const [infoClass, setInfoClass] = useState({disciplina: 'PCS1234', 
                                              nome: '2_2024_PCS1234',
                                              inicio: '12/03/2024',
                                              fim: '13/07/2024',
                                              alunosInscritos: '20'});                                        
  const [value, setValue] = useState('option1');

  

  const handleSearch = async () => {
    try {
      //const response = await fetch(`api/turmas/${idDisciplina}`);
      const response = await fetch(`colocar onde pegar`);
      const data = await response.json();

      console.log('Fetched Class:', data.name);
      setInfoClass(data);
    } catch (error) {
      console.error('Error fetching classes:', error);
    }
  };

  return (
    <Box>
      <StyledHead> Salas para alocação: </StyledHead>
      <StyledBox>
        <Text textAlign="center">Segunda-feira: 08:00 - 10:00</Text>
        <Box p={4}>
          <RadioGroup value={value} onChange={setValue}>
            <Box display="flex" flexDirection="row" gap="20px">
              <RadioCard value="option1" label="Sala: GD1-02                          |         Capacidade: 30       |                            Acessibilidade: Não              |               Computadores: 30             |          Sistema Operacional: Linux                 |                              Técnico Responsável: Michelet  |  Conflito: Sim" />
              <RadioCard value="option2" label="Sala: GC1-15                          |         Capacidade: 50       |                            Acessibilidade: Não              |               Computadores: 0              |          Sistema Operacional: -                     |                              Técnico Responsável: -         |  Conflito: Sim" />
              <RadioCard value="option3" label="Sala: GD2-10                          |         Capacidade: 25       |                            Acessibilidade: Sim              |               Computadores: 25             |          Sistema Operacional: Windows               |                              Técnico Responsável: Daniel    |  Conflito: Não" />
            </Box>
          </RadioGroup>
        </Box>
      </StyledBox>
    </Box>
  );
};

export default InformacoesTurma;
