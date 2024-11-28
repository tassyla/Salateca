import React, { useState } from 'react';
import { Flex, Text } from '@chakra-ui/react';
import { StyledBox, StyledTitle, StyledInput, StyledButton, StyledSelect } from './ClassroomAllocation.styles';
import { useNavigate } from 'react-router-dom'; // Importa o hook de navegação

const ClassroomAllocation = () => {
  const [idDisciplina, setidDisciplina] = useState('');
  const [classes, setClasses] = useState<string[]>([]);
  const [selectedClass, setSelectedClass] = useState('');
  const [loading, setLoading] = useState(false);  // Estado para controlar o carregamento
  const [error, setError] = useState<string>(''); // Estado para capturar erros

  const navigate = useNavigate(); // Inicializa o hook de navegação

  const handleSearch = async () => {
    if (!idDisciplina) {
      alert('Por favor, insira o código da disciplina');
      return;
    }
    setLoading(true); // Inicia o carregamento
    setError(''); // Limpa erros anteriores

    try {
      // Substituindo a URL pela dinâmica com idDisciplina
      const response = await fetch(`http://localhost:8080/salateca/turmas/listar/${idDisciplina}`);

      // Verificando se a resposta foi bem-sucedida
      if (!response.ok) {
        throw new Error('Falha ao buscar as turmas');
      }

      const data = await response.json();
      console.log(data)

      // Se a API retornar as turmas no formato esperado
      if (Array.isArray(data)) {
        const classNames = data.map((classItem: { codigo: string }) => classItem.codigo);
        setClasses(classNames); // Atualiza o estado com os nomes das turmas
      } else {
        throw new Error('Formato de dados inválido');
      }
    } catch (error: any) {
      console.error('Error fetching classes:', error);
      setError(error.message); // Captura qualquer erro
    } finally {
      setLoading(false); // Finaliza o carregamento
    }
  };

  const handleNext = () => {
    if (!selectedClass) {
      alert('Por favor, selecione uma turma');
      return;
    }

    console.log('Selected Class:', selectedClass);
    navigate('/teste');  // Navega para a próxima tela
  };

  return (
    <StyledBox>
      <StyledTitle>Alocar salas a uma turma</StyledTitle>
      <Text fontSize="18px" mb="8px">Código da Disciplina</Text>
      <StyledInput 
        placeholder="Digite o código da disciplina"
        value={idDisciplina}
        onChange={(e: any) => setidDisciplina(e.target.value)}
      />
      <StyledButton onClick={handleSearch} isLoading={loading}>Pesquisar</StyledButton>

      {error && <Text color="red.500" mt={2}>{error}</Text>}

      {classes.length > 0 && (
        <>
          <Text fontSize="18px" mb="8px">Selecione a Turma</Text>
          <StyledSelect 
            placeholder="Selecione uma turma" 
            value={selectedClass}
            onChange={(e: any) => setSelectedClass(e.target.value)}
          >
            {classes.map((className) => (
              <option key={className} value={className}>{className}</option>
            ))}
          </StyledSelect>
          <StyledButton onClick={handleNext}>Próximo</StyledButton>
        </>
      )}

      {loading && <Text mt={2}>Carregando turmas...</Text>}
    </StyledBox>
  );
};

export default ClassroomAllocation;
