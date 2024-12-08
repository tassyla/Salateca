import React from 'react';
import { Box } from '@chakra-ui/react';
import ClassInformationBox from './ClassInformationBox';
import ClassSelection from './ClassSelection';

const AllocationInformation = () => (
    <Box>
      <ClassInformationBox/>
      <ClassSelection/>
    </Box>
  );
  
  export default AllocationInformation;