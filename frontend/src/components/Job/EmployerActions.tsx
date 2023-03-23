import { Box, Flex } from '@chakra-ui/react';
import { Link as RouterLink } from 'react-router-dom';
import { AiOutlineEdit } from 'react-icons/ai';
import { BsTrash } from 'react-icons/bs';
import { Tooltip } from '@chakra-ui/react';
import { AxiosError } from 'axios';
import { http } from '../../helpers/utils';

interface IEmployerActionsProps {
  employerId: number;
  jobId: number;
}

const EmployerActions = ({ employerId, jobId }: IEmployerActionsProps) => {
  const deleteJob = async () => {
    try {
      const response = await http.delete(`/jobs/${jobId}`);
    } catch (err: unknown | AxiosError) {
      if (err instanceof AxiosError && err.response) {
        console.log(err.response);
      }
    }
  };

  const openModal = () => {};

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
          <Box onClick={openModal} cursor="pointer" mx="0.5rem">
            <BsTrash />
          </Box>
        </Tooltip>
      </Flex>
    </>
  );
};

export default EmployerActions;
