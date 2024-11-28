import styled from 'styled-components';
import { Box, Link } from '@chakra-ui/react';

export const StyledBox = styled(Box)`
  background-color: #04044c;
  width: 100%;
  padding: 16px;
  color: white;
  font-size: calc(10px + 2vmin);
  align-items: center;
`;

export const StyledLink = styled(Link)`
  position: relative;
  padding: 15px;
  font-size: calc(5px + 2vmin);
  text-decoration: none;

  &:hover {
    text-decoration: dashed;
  }

  &::after { // creates a line at the bottom of the link
    content: '';
    position: absolute;
    left: 0;
    bottom: -16px; 
    width: 100%;
    height: 2px;
    background-color: #FABD05;
    transition: height 0.3s ease, border-radius 0.3s ease;
  }

  &:hover::after {
    height: 8px;
    border-radius: 8px 8px 0 0;
  }
`;
