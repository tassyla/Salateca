import React from 'react';
import './App.css';
import { ChakraProvider } from '@chakra-ui/react'
import Menu from './components/Menu';

function App() {

  return (
    <ChakraProvider>
      <div className="App">
      <header className="App-header">
        <Menu />
      </header>
    </div>
    </ChakraProvider>
  )
}

export default App;
