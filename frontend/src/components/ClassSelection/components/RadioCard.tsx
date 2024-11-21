import React from 'react';
import { Box, Radio, RadioProps } from '@chakra-ui/react';

interface RadioCardProps extends RadioProps {
  label: string;
  value: string;
}

const RadioCard: React.FC<RadioCardProps> = ({ value, label, ...props }) => {
  return (
    <Box
      as="label"
      display="flex"
    //   flexDirection="line"
    //   alignItems="center"
    //   justifyContent="center"
      padding="16px"
      borderWidth="2px"
      borderColor="gray.300"
      borderRadius="20px"
      cursor="pointer"
      _hover={{ borderColor: 'blue.500' }}
      _checked={{
        borderColor: 'blue.500',
        backgroundColor: 'blue.100',
      }}
      transition="all 0.3s"
      {...props} 
    >
      <Radio value={value} />
      <Box marginTop="8px" fontWeight="bold">
        {label}
      </Box>
    </Box>
  );
};

export default RadioCard;