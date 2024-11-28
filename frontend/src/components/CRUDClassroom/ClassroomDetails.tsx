import React from 'react';
import { Box, Button, Modal, ModalBody, ModalCloseButton, ModalContent, ModalHeader, ModalOverlay, Text } from '@chakra-ui/react';
import { SalaDeAula } from '../../types';

type SalaDetalhesProps = {
  sala: SalaDeAula;
  isOpen: boolean;
  onClose: () => void;
  setSalaSelecionada: React.Dispatch<React.SetStateAction<SalaDeAula | null>>;
};

const SalaDetalhes: React.FC<SalaDetalhesProps> = ({ sala, isOpen, onClose, setSalaSelecionada }) => {
  const handleEdit = () => {
    alert('Abrir formulário de edição da sala');
    // Aqui você pode redirecionar para um formulário de edição
  };

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
            <Text><strong>Capacidade:</strong> {sala.capacidade}</Text>
            <Text><strong>Número de Computadores:</strong> {sala.numeroComputadores}</Text>
            <Text><strong>Sistema Operacional:</strong> {sala.sistemaOperacional}</Text>
            <Text><strong>Técnico Responsável:</strong> {sala.tecnico}</Text>

            <Button colorScheme="blue" onClick={handleEdit} mr={3}>
              Editar
            </Button>
            <Button colorScheme="red" onClick={handleDelete}>
              Excluir
            </Button>
          </Box>
        </ModalBody>
      </ModalContent>
    </Modal>
  );
};

export default SalaDetalhes;
