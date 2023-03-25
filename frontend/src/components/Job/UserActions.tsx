import {
  Box,
  Flex,
  Tooltip,
  Button,
  Popover,
  PopoverTrigger,
  PopoverContent,
  PopoverArrow,
  PopoverCloseButton,
  PopoverHeader,
  PopoverBody,
  Text,
} from '@chakra-ui/react';
import { useContext } from 'react';
import { UserContext } from '../../context/user';
import { IUserContext } from '../../interfaces';
import { AiOutlineHeart, AiFillHeart } from 'react-icons/ai';
import { BsThreeDotsVertical } from 'react-icons/bs';
import { AxiosError } from 'axios';
import { http } from '../../helpers/utils';

interface IUserActionProps {
  jobId: number;
}

const UserActions = ({ jobId }: IUserActionProps) => {
  const { user } = useContext(UserContext) as IUserContext;

  const saveJob = async (e: React.MouseEvent<HTMLDivElement>) => {
    e.stopPropagation();
    try {
      const response = await http.post('/saved-jobs/', {
        userId: user.id,
        jobId,
      });
      console.log(response);
    } catch (err: unknown | AxiosError) {
      if (err instanceof AxiosError && err.response) {
        console.log(err.response);
      }
    }
  };

  return (
    <Flex fontSize="1.5rem" justifyContent="flex-end">
      <Popover>
        <PopoverTrigger>
          <Box as="span" cursor="pointer">
            <BsThreeDotsVertical />
          </Box>
        </PopoverTrigger>
        <PopoverContent>
          <PopoverArrow />
          <PopoverBody width="50px">
            <Box cursor="pointer" onClick={saveJob}>
              <Tooltip label="Save job" fontSize="md">
                <Box as="span">
                  <AiOutlineHeart color="red" />
                </Box>
              </Tooltip>
            </Box>
          </PopoverBody>
          <PopoverCloseButton />
        </PopoverContent>
      </Popover>
    </Flex>
  );
};

export default UserActions;
