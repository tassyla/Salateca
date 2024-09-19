import React from 'react';
import { Flex } from '@chakra-ui/react';
import { StyledBox, StyledLink } from './Menu.styles';

const Menu = () => (
  <StyledBox>
    <Flex justify="flex-end">
      <StyledLink href="#home">Home</StyledLink>
      <StyledLink href="#about">About</StyledLink>
      <StyledLink href="#services">Services</StyledLink>
      <StyledLink href="#contact">Contact</StyledLink>
    </Flex>
  </StyledBox>
);

export default Menu;