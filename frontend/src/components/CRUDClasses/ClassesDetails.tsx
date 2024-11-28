import React, { useState } from 'react';
import { Box, Button, Checkbox, Input, Modal, ModalBody, ModalCloseButton, ModalContent, ModalFooter, ModalHeader, ModalOverlay, Text } from '@chakra-ui/react';
import { Turma } from '../../types';

type TurmaDetalhesProps = {
  turma: Turma;
  isOpen: boolean;
  onClose: () => void;
  setTurmaSelecionada: React.Dispatch<React.SetStateAction<Turma | null>>;
};

const TurmaDetalhes: React.FC<TurmaDetalhesProps> = ({ turma, isOpen, onClose, setTurmaSelecionada }) => {
  // Estado para armazenar os dados da turma sendo editada
  const [turmaEditada, setTurmaEditada] = useState<Turma>(turma);

  // Função para lidar com a mudança nos campos de texto
  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const { name, value } = e.target;
    setTurmaEditada((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  // Função para lidar com a mudança no checkbox de acessibilidade
  const handleCheckboxChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setTurmaEditada((prev) => ({
      ...prev,
      acessibilidade: e.target.checked,
    }));
  };

  // Função para salvar as alterações
  const handleSave = () => {
    setTurmaSelecionada(turmaEditada);
    onClose();
  };

  // Função para excluir a turma
  const handleDelete = () => {
    const confirmDelete = window.confirm('Você tem certeza que deseja excluir essa turma?');
    if (confirmDelete) {
      alert('Turma excluída');
      // Aqui você poderia excluir a turma da lista ou do banco de dados
      setTurmaSelecionada(null);
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
            <Text><strong>Código:</strong> {turma.codigo}</Text>

            <Text fontWeight="bold" mt={4}>Disciplina</Text>
            <Input
              value={turmaEditada.disciplina}
              onChange={handleChange}
              name="disciplina"
            />

            <Text fontWeight="bold" mt={4}>Data de Início</Text>
            <Input
              value={turmaEditada.dataInicio}
              onChange={handleChange}
              name="dataInicio"
              type="date"
            />

            <Text fontWeight="bold" mt={4}>Data de Fim</Text>
            <Input
              value={turmaEditada.dataFim}
              onChange={handleChange}
              name="dataFim"
              type="date"
            />

            <Text fontWeight="bold" mt={4}>Horários</Text>
            <Input
              value={turmaEditada.horarios}
              onChange={handleChange}
              name="horarios"
            />

            <Text fontWeight="bold" mt={4}>Número de Alunos</Text>
            <Input
              value={turmaEditada.numAlunos}
              onChange={handleChange}
              name="numAlunos"
              type="number"
            />

            <Text fontWeight="bold" mt={4}>Frequência</Text>
            <Input
              value={turmaEditada.frequencia}
              onChange={handleChange}
              name="frequencia"
            />

            <Text fontWeight="bold" mt={4}>Professor</Text>
            <Input
              value={turmaEditada.professor}
              onChange={handleChange}
              name="professor"
            />

            <Text fontWeight="bold" mt={4}>Acessibilidade</Text>
            <Checkbox
              isChecked={turmaEditada.acessibilidade}
              onChange={handleCheckboxChange}
            >
              Turma acessível
            </Checkbox>

            <Text fontWeight="bold" mt={4}>Computadores Disponíveis</Text>
            <Checkbox
              isChecked={turmaEditada.computadores}
              onChange={(e) => {
                setTurmaEditada((prev) => ({
                  ...prev,
                  computadores: e.target.checked,
                }));
              }}
            >
              Computadores disponíveis
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

export default TurmaDetalhes;
