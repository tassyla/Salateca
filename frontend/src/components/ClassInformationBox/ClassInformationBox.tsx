import React, { useState } from 'react';
import { List, ListItem, Divider } from '@chakra-ui/react';
import { StyledBox, StyledTitle, StyledSubtitle } from './ClassInformationBox.styles';


const InformacoesTurma = () => {
  const [infoClass, setInfoClass] = useState({disciplina: 'teste', 
                                              nome: 'Turma Teste',
                                              inicio: 'xx/xx/xxx',
                                              fim: 'xx/xx/xxxx',
                                              alunosInscritos: '0'});

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
    <StyledBox>
      <StyledTitle>  {infoClass.nome} </StyledTitle>

      <StyledSubtitle>
        Disciplina: <span style={{ fontWeight: 'normal', color: 'black' }}>{infoClass.disciplina}</span>
      </StyledSubtitle>

      <StyledSubtitle>
        Início: <span style={{ fontWeight: 'normal', color: 'black' }}>{infoClass.inicio}</span> -  
        Fim: <span style={{ fontWeight: 'normal', color: 'black' }}>{infoClass.fim}</span>
      </StyledSubtitle>

      <StyledSubtitle>
        Número de alunos inscritos: <span style={{ fontWeight: 'normal', color: 'black' }}>{infoClass.alunosInscritos}</span>
      </StyledSubtitle>

      <Divider my="2" />

      <StyledSubtitle>
        Horários de aula:
      </StyledSubtitle>

      <List spacing={2}>
        <ListItem>Segunda-feira: 08:00 - 10:00</ListItem>
        <ListItem>Quarta-feira: 10:00 - 12:00</ListItem>
        <ListItem>Sexta-feira: 14:00 - 16:00</ListItem>
      </List>
    </StyledBox>
  );
};

export default InformacoesTurma;
