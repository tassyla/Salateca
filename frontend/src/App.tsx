import React from 'react';
import './App.css';
import { ChakraProvider } from '@chakra-ui/react'
import Menu from './components/Menu';
import ClassroomAllocation from './components/ClassroomAllocation';
import ClassInformationBox from './components/ClassInformationBox';

function App() {

  return (
    <ChakraProvider>
      <div className="App">
      <header className="App-header">
        <Menu />
        <ClassroomAllocation />
        <ClassInformationBox />

      </header>
    </div>
    </ChakraProvider>
  )
}

export default App;
