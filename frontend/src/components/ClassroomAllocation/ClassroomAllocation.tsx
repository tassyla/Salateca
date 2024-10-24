import React, { useState } from 'react';
import { Flex, Text } from '@chakra-ui/react';
import { StyledBox, StyledTitle, StyledInput, StyledButton, StyledSelect } from './ClassroomAllocation.styles';

const ClassroomAllocation = () => {
  const [idDisciplina, setidDisciplina] = useState('');
  const [classes, setClasses] = useState<string[]>([]);
  const [selectedClass, setSelectedClass] = useState('');

  const handleSearch = async () => {
    try {
      //const response = await fetch(`api/turmas/${idDisciplina}`);
      const response = await fetch(`/salateca/api/turmas/123`);
      const data = await response.json();
      
      const classNames = data.map((classItem: {nome: string}) => classItem.nome);
      console.log('Fetched Classes:', classNames);
      setClasses(classNames);
    } catch (error) {
      console.error('Error fetching classes:', error);
    }
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
        value={idDisciplina}
        onChange={(e) => setidDisciplina(e.target.value)}
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
