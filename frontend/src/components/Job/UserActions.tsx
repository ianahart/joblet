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
import { useContext, useEffect, useState } from 'react';
import { UserContext } from '../../context/user';
import { ISyncSavedJobResponse, IUserContext } from '../../interfaces';
import { AiOutlineHeart, AiFillHeart } from 'react-icons/ai';
import { BsThreeDotsVertical } from 'react-icons/bs';
import { Axios, AxiosError } from 'axios';
import { http } from '../../helpers/utils';
import { useEffectOnce } from '../../hooks/UseEffectOnce';

interface IUserActionProps {
  jobId: number;
  employerId: number;
}

const UserActions = ({ jobId, employerId }: IUserActionProps) => {
  const { user } = useContext(UserContext) as IUserContext;
  const [isSaved, setIsSaved] = useState(false);
  const [savedJobId, setSavedJobId] = useState(0);

  const saveJob = async (e: React.MouseEvent<HTMLDivElement>) => {
    e.stopPropagation();
    try {
      const response = await http.post('/saved-jobs/', {
        userId: user.id,
        jobId,
        employerId,
      });
      setIsSaved(true);
    } catch (err: unknown | AxiosError) {
      if (err instanceof AxiosError && err.response) {
        return;
      }
    }
  };

  useEffect(() => {
    if (jobId !== 0) {
      const syncSavedJob = async () => {
        const response = await http.get<ISyncSavedJobResponse>(
          `/saved-jobs/sync/?jobId=${jobId}`
        );
        setIsSaved(response.data.isSaved);
        setSavedJobId(response.data.savedJobId);
      };

      syncSavedJob();
    }
  }, [jobId]);

  const unSaveJob = async () => {
    try {
      const response = await http.delete(`/saved-jobs/${savedJobId}`);
      setIsSaved(false);
    } catch (err: unknown | AxiosError) {
      if (err instanceof AxiosError && err.response) {
        return;
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
            {isSaved ? (
              <Box cursor="pointer" onClick={unSaveJob}>
                <Tooltip label="Unsave job" fontSize="md">
                  <Box as="span">
                    <AiFillHeart color="red" />
                  </Box>
                </Tooltip>
              </Box>
            ) : (
              <Box cursor="pointer" onClick={saveJob}>
                <Tooltip label="Save job" fontSize="md">
                  <Box as="span">
                    <AiOutlineHeart color="red" />
                  </Box>
                </Tooltip>
              </Box>
            )}
          </PopoverBody>
          <PopoverCloseButton />
        </PopoverContent>
      </Popover>
    </Flex>
  );
};

export default UserActions;
