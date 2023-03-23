import { Box, Flex } from '@chakra-ui/react';
import { Link as RouterLink } from 'react-router-dom';
import { AiOutlineEdit } from 'react-icons/ai';
import { BsTrash } from 'react-icons/bs';
import { Tooltip } from '@chakra-ui/react';
import { AxiosError } from 'axios';
import { http } from '../../helpers/utils';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import BasicModal from '../Modal/BasicModal';

interface IEmployerActionsProps {
  employerId: number;
  jobId: number;
  jobPosition: string;
}

const EmployerActions = ({ employerId, jobId, jobPosition }: IEmployerActionsProps) => {
  const navigate = useNavigate();
  const [modalOpen, setModalOpen] = useState(false);
  const deleteJob = async () => {
    try {
      const response = await http.delete(`/jobs/owner/${jobId}`);
      navigate('/employer-jobs');
    } catch (err: unknown | AxiosError) {
      if (err instanceof AxiosError && err.response) {
        return;
      }
    }
  };

  const handleOpenModal = (e: React.MouseEvent<HTMLDivElement>) => {
    e.stopPropagation();
    setModalOpen(true);
  };

  return (
    <>
      <Flex justifyContent="flex-end" fontSize="1.5rem">
        <Box mx="0.5rem">
          <Tooltip label="Edit Job">
            <RouterLink to={`/update-job/${jobId}`}>
              <AiOutlineEdit />
            </RouterLink>
          </Tooltip>
        </Box>
        <Tooltip label="Delete Job">
          <Box onClick={(e) => handleOpenModal(e)} cursor="pointer" mx="0.5rem">
            <BsTrash />
          </Box>
        </Tooltip>
      </Flex>
      <BasicModal
        setModalOpen={setModalOpen}
        deleteResource={deleteJob}
        leftBtnText="I'm sure"
        rightBtnText="Cancel"
        text={`Are you sure you want to delete the ${jobPosition} position?`}
        modalOpen={modalOpen}
        h="200px"
        w={['95%', '95%', '350px']}
      />
    </>
  );
};

export default EmployerActions;
