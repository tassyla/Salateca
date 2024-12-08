import React, { useState } from 'react';
import { Box, Button, Checkbox, Input, Modal, ModalBody, ModalCloseButton, ModalContent, ModalFooter, ModalHeader, ModalOverlay, Text, Textarea } from '@chakra-ui/react';
import { SalaDeAula } from '../../types';

type SalaDetalhesProps = {
  sala: SalaDeAula;
  isOpen: boolean;
  onClose: () => void;
  setSalaSelecionada: React.Dispatch<React.SetStateAction<SalaDeAula | null>>;
};

const SalaDetalhes: React.FC<SalaDetalhesProps> = ({ sala, isOpen, onClose, setSalaSelecionada }) => {
  // Estado para armazenar os dados da sala sendo editada
  const [salaEditada, setSalaEditada] = useState<SalaDeAula>(sala);

  // Função para lidar com a mudança nos campos de texto
  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const { name, value } = e.target;
    setSalaEditada((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  // Função para lidar com a mudança no checkbox
  const handleCheckboxChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSalaEditada((prev) => ({
      ...prev,
      acessibilidade: e.target.checked,
    }));
  };

  // Função para salvar as alterações
  const handleSave = () => {
    setSalaSelecionada(salaEditada);
    onClose();
  };

  // Função para excluir a sala
  const handleDelete = () => {
    const confirmDelete = window.confirm('Você tem certeza que deseja excluir essa sala?');
    if (confirmDelete) {
      alert('Sala excluída');
      // Aqui você poderia excluir a sala da lista ou do banco de dados
      setSalaSelecionada(null);
      onClose();
    }
  };

  return (
    <Modal isOpen={isOpen} onClose={onClose}>
      <ModalOverlay />
      <ModalContent>
        <ModalHeader>Detalhes da Sala</ModalHeader>
        <ModalCloseButton />
        <ModalBody>
          <Box>
            <Text><strong>Código:</strong> {sala.codigo}</Text>

            <Text fontWeight="bold" mt={4}>Código da Sala</Text>
            <Input
              value={salaEditada.codigo}
              onChange={handleChange}
              name="codigo"
              isReadOnly
            />

            <Text fontWeight="bold" mt={4}>Capacidade</Text>
            <Input
              value={salaEditada.capacidade}
              onChange={handleChange}
              name="capacidade"
              type="number"
            />

            <Text fontWeight="bold" mt={4}>Número de Computadores</Text>
            <Input
              value={salaEditada.numeroComputadores}
              onChange={handleChange}
              name="numeroComputadores"
              type="number"
            />

            <Text fontWeight="bold" mt={4}>Sistema Operacional</Text>
            <Input
              value={salaEditada.sistemaOperacional}
              onChange={handleChange}
              name="sistemaOperacional"
            />

            <Text fontWeight="bold" mt={4}>Técnico Responsável</Text>
            <Input
              value={salaEditada.tecnico}
              onChange={handleChange}
              name="tecnico"
            />

            <Text fontWeight="bold" mt={4}>Acessibilidade</Text>
            <Checkbox
              isChecked={salaEditada.acessibilidade}
              onChange={handleCheckboxChange}
            >
              Sala acessível
            </Checkbox>
          </Box>
        </ModalBody>

        <ModalFooter>
          <Button variant="ghost" mr={3} onClick={onClose}>
            Cancelar
          </Button>
          <Button colorScheme="blue" onClick={handleSave}>
            Salvar
          </Button>
          <Button colorScheme="red" onClick={handleDelete}>
            Excluir
          </Button>
        </ModalFooter>
      </ModalContent>
    </Modal>
  );
};

export default SalaDetalhes;
