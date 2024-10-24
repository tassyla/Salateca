import React, { useState } from 'react';
import { Flex, Text } from '@chakra-ui/react';
import { StyledBox, StyledTitle, StyledInput, StyledButton, StyledSelect } from './ClassroomAllocation.styles';

const ClassroomAllocation = () => {
  const [disciplineId, setDisciplineId] = useState('');
  const [classes, setClasses] = useState<string[]>([]);
  const [selectedClass, setSelectedClass] = useState('');

  const handleSearch = () => {
    // Exemplos de Turmas
    setClasses(['Class 1', 'Class 2', 'Class 3']);
  };

  const handleNext = () => {
    console.log('Selected Class:', selectedClass);
  };

  return (
    <StyledBox>
      <StyledTitle>Alocar salas a uma turma</StyledTitle>
      <Text fontSize="18px" mb="8px">Código da Disciplina</Text>
      <StyledInput 
        placeholder="Digite o código da disciplina"
        value={disciplineId}
        onChange={(e) => setDisciplineId(e.target.value)}
      />
      <StyledButton onClick={handleSearch}>Pesquisar</StyledButton>
      
      {classes.length > 0 && (
        <>
          <Text fontSize="18px" mb="8px">Selecione a Turma</Text>
          <StyledSelect 
            placeholder="Selecione uma turma" 
            value={selectedClass}
            onChange={(e) => setSelectedClass(e.target.value)}
          >
            {classes.map((className) => (
              <option key={className} value={className}>{className}</option>
            ))}
          </StyledSelect>
          <StyledButton onClick={handleNext}>Próximo</StyledButton>
        </>
      )}
    </StyledBox>
  );
};

export default ClassroomAllocation;
