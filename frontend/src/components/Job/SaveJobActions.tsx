import { AiOutlineHeart, AiFillHeart } from 'react-icons/ai';
import { Flex, Box, Tooltip } from '@chakra-ui/react';
import { useContext } from 'react';
import { UserContext } from '../../context/user';
import { IUserContext } from '../../interfaces';

interface ISaveActionsProps {
  jobId: number;
}

const SaveJobActions = ({ jobId }: ISaveActionsProps) => {
  const { user } = useContext(UserContext) as IUserContext;

  return (
    <Flex fontSize="1.5rem" justifyContent="flex-end">
      <Box>
        <Tooltip label="Unsave" fontSize="md">
          <Box as="span">
            <AiOutlineHeart color="red" />
          </Box>
        </Tooltip>
      </Box>
    </Flex>
  );
};

export default SaveJobActions;
