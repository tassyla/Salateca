import React, { useState } from 'react';
import { Box, RadioGroup } from '@chakra-ui/react';
import { StyledHead, StyledBox } from './ClassSelection.styles';
import RadioCard from './components/RadioCard'

const InformacoesTurma = () => {
  const [infoClass, setInfoClass] = useState({disciplina: 'teste', 
                                              nome: 'Turma Teste',
                                              inicio: 'xx/xx/xxx',
                                              fim: 'xx/xx/xxxx',
                                              alunosInscritos: '0'});                                        
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
        <Box p={4}>
          <RadioGroup value={value} onChange={setValue}>
            <Box display="flex" flexDirection="row" gap="20px">
              <RadioCard value="option1" label="Option 1" />
              <RadioCard value="option2" label="Option 2" />
              <RadioCard value="option3" label="Option 3" />
            </Box>
          </RadioGroup>
        </Box>
      </StyledBox>
    </Box>
  );
};

export default InformacoesTurma;
