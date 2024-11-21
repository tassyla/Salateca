import React from 'react';
import { Flex } from '@chakra-ui/react';
import { StyledBox, StyledLink } from './Menu.styles';

const Menu = () => (
  <StyledBox>
    <Flex justify="flex-end">
      <StyledLink href="#home">Home</StyledLink>
      <StyledLink href="#about">Sobre</StyledLink>
      <StyledLink href="#services">Salas</StyledLink>
      <StyledLink href="#contact">Perfil</StyledLink>
    </Flex>
  </StyledBox>
);

export default Menu;