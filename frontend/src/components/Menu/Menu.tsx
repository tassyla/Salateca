import React from 'react';
import { Flex } from '@chakra-ui/react';
import { StyledBox, StyledLink } from './Menu.styles';

const Menu = () => (
  <StyledBox>
    <Flex justify="flex-end">
      <StyledLink href="/home">Home</StyledLink>
      <StyledLink href="/teste">Teste</StyledLink>
      <StyledLink href="/turmas">Turmas</StyledLink>
      <StyledLink href="/salas">Salas</StyledLink>
      <StyledLink href="/alocacoes">Alocações</StyledLink>
      <StyledLink href="/perfil">Perfil</StyledLink>

    </Flex>
  </StyledBox>
);

export default Menu;