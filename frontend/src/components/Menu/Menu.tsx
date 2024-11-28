import React from 'react';
import { Flex } from '@chakra-ui/react';
import { StyledBox, StyledLink } from './Menu.styles';

const Menu = () => (
  <StyledBox>
    <Flex justify="flex-end">
      <StyledLink href="/home">Criar Alocação</StyledLink>
      <StyledLink href="/alocacoes">Alocações</StyledLink>
      <StyledLink href="/turmas">Turmas</StyledLink>
      <StyledLink href="/salas">Salas</StyledLink>

    </Flex>
  </StyledBox>
);

export default Menu;