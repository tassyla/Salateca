import React from 'react';
import { Box, Button, Modal, ModalBody, ModalCloseButton, ModalContent, ModalHeader, ModalOverlay, Text } from '@chakra-ui/react';
import { Turma } from '../../types';

type SalaDetalhesProps = {
  sala: Turma;
  isOpen: boolean;
  onClose: () => void;
  setSalaSelecionada: React.Dispatch<React.SetStateAction<Turma | null>>;
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
        <ModalHeader>Detalhes da Turma</ModalHeader>
        <ModalCloseButton />
        <ModalBody>
          <Box>
            <Text><strong>Código:</strong> {sala.codigo}</Text>
            <Text><strong>Disciplina:</strong> {sala.disciplina}</Text>
            <Text><strong>Data de Início:</strong> {sala.dataInicio}</Text>
            <Text><strong>Data de Fim:</strong> {sala.dataFim}</Text>
            <Text><strong>Horários:</strong> {sala.horarios}</Text>
            <Text><strong>Número de Alunos:</strong> {sala.numAlunos}</Text>
            <Text><strong>Frequência:</strong> {sala.frequencia}</Text>
            <Text><strong>Professor:</strong> {sala.professor}</Text>
            <Text><strong>Acessibilidade:</strong> {sala.acessibilidade ? 'Sim' : 'Não'}</Text>
            <Text><strong>Computadores:</strong> {sala.computadores ? 'Sim' : 'Não'}</Text>

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
