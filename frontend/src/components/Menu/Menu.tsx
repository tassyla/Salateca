import React from 'react';
import { Flex, Text } from '@chakra-ui/react';
import { StyledBox, StyledLink } from './Menu.styles';

const Menu = () => (
  <StyledBox>
    <Flex justify="space-between">
      <Flex>
        <Text fontSize="2xl" mb={4} fontWeight="bold">Salateca</Text>
      </Flex>
      <Flex justify="flex-end">
        <StyledLink href="/home">Criar Alocação</StyledLink>
        <StyledLink href="/alocacoes">Alocações</StyledLink>
        <StyledLink href="/turmas">Turmas</StyledLink>
        <StyledLink href="/salas">Salas</StyledLink>
        <StyledLink href="/teste"></StyledLink>

      </Flex>
    </Flex>
  </StyledBox>
);

export default Menu;