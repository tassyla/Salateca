import React, { useState } from 'react';
import { Button, FormControl, FormLabel, Input, Select, VStack, Box, Heading } from '@chakra-ui/react';

interface Sala {
    id: number;
    nome: string;
    capacidade: number;
    numeroComputadores: number;
    sistemaOperacional: string;
    acessibilidade: boolean;
    tecnico: string;
  }
  
  // Dados iniciais para exemplo
  const salasData: Sala[] = [
    { id: 1, nome: 'Sala 101', capacidade: 30, numeroComputadores: 15, sistemaOperacional: 'Windows', acessibilidade: true, tecnico: 'Carlos' },
    { id: 2, nome: 'Sala 102', capacidade: 25, numeroComputadores: 10, sistemaOperacional: 'Linux', acessibilidade: false, tecnico: 'Ana' },
    { id: 3, nome: 'Sala 103', capacidade: 40, numeroComputadores: 20, sistemaOperacional: 'MacOS', acessibilidade: true, tecnico: 'Roberto' },
  ];  


const EditRoomForm = () => {
    const [salas, setSalas] = useState<Sala[]>(salasData); // Estado que contém as salas
    const [selectedSala, setSelectedSala] = useState<Sala | null>(null); // Sala que está sendo editada
    const [formData, setFormData] = useState<Sala | null>(null); // Dados do formulário
    
    // Função para selecionar a sala que será editada
    const handleEdit = (sala: Sala) => {
      setSelectedSala(sala);
      setFormData({ ...sala }); // Preenche o formulário com os dados da sala selecionada
    };
  
    // Função para lidar com a alteração dos campos no formulário
    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
      const { name, value } = e.target;
      if (formData) {
        setFormData({
          ...formData,
          [name]: name === 'acessibilidade' ? value === 'true' : value,
        });
      }
    };
  
    // Função para salvar a edição
    const handleSave = () => {
      if (formData && selectedSala) {
        const updatedSalas = salas.map(sala => 
          sala.id === selectedSala.id ? { ...sala, ...formData } : sala
        );
        setSalas(updatedSalas);
        setSelectedSala(null);
        setFormData(null);
      }
    };
  
    return (
      <Box p={4}>
        <Heading mb={6}>Editar Sala</Heading>
        
        {/* Lista de Salas */}
        <VStack spacing={4} align="start">
          {salas.map((sala) => (
            <Button key={sala.id} onClick={() => handleEdit(sala)} colorScheme="teal">
              Editar {sala.nome}
            </Button>
          ))}
        </VStack>
        
        {/* Formulário de Edição */}
        {selectedSala && formData && (
          <Box mt={8}>
            <Heading size="md" mb={4}>Editar Dados da {selectedSala.nome}</Heading>
            
            <VStack spacing={4} align="start">
              <FormControl id="capacidade">
                <FormLabel>Capacidade</FormLabel>
                <Input
                  type="number"
                  name="capacidade"
                  value={formData.capacidade}
                  onChange={handleChange}
                  placeholder="Capacidade da sala"
                />
              </FormControl>
              
              <FormControl id="numeroComputadores">
                <FormLabel>Número de Computadores</FormLabel>
                <Input
                  type="number"
                  name="numeroComputadores"
                  value={formData.numeroComputadores}
                  onChange={handleChange}
                  placeholder="Número de Computadores"
                />
              </FormControl>
  
              <FormControl id="sistemaOperacional">
                <FormLabel>Sistema Operacional</FormLabel>
                <Input
                  type="text"
                  name="sistemaOperacional"
                  value={formData.sistemaOperacional}
                  onChange={handleChange}
                  placeholder="Sistema Operacional"
                />
              </FormControl>
  
              <FormControl id="acessibilidade">
                <FormLabel>Acessibilidade</FormLabel>
                <Select
                  name="acessibilidade"
                  value={formData.acessibilidade ? 'true' : 'false'}
                  onChange={handleChange}
                >
                  <option value="true">Sim</option>
                  <option value="false">Não</option>
                </Select>
              </FormControl>
  
              <FormControl id="tecnico">
                <FormLabel>Técnico Responsável</FormLabel>
                <Input
                  type="text"
                  name="tecnico"
                  value={formData.tecnico}
                  onChange={handleChange}
                  placeholder="Nome do técnico responsável"
                />
              </FormControl>
  
              <Button colorScheme="teal" onClick={handleSave}>
                Salvar Alterações
              </Button>
            </VStack>
          </Box>
        )}
      </Box>
    );
  };
  
  export default EditRoomForm;