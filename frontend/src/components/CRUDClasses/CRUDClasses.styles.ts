import styled from 'styled-components';
import { Box, Button, Input, Select, Text } from '@chakra-ui/react';

export const StyledBox = styled(Box)`
  background-color: #f0f0f5;
  border-radius: 20px;
  padding: 20px;
  width: 100%;
  max-width: 600px;
  margin: 20px auto;
`;

export const StyledTitle = styled(Text)`
  font-size: 24px;
  font-weight: bold;
  color: #04044c;
  text-align: center;
  margin-bottom: 20px;
`;

export const StyledInput = styled(Input)`
  margin-bottom: 16px;
  font-size: 18px;
`;

export const StyledButton = styled(Button)`
  background-color: #FABD05;
  color: #04044c;
  margin-top: 10px;

  &:hover {
    background-color: #e0a604;
  }
`;

export const StyledSelect = styled(Select)`
  margin-bottom: 16px;
  font-size: 18px;
`;
