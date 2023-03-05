import { Box } from '@chakra-ui/react';
import { AxiosError } from 'axios';
import { http, retreiveTokens } from '../helpers/utils';
import { useEffectOnce } from '../hooks/UseEffectOnce';

const Joblet = () => {
  const fetchJobPosts = async () => {
    try {
      const response = await http.get('/posts/');
      console.log(response);
    } catch (err: unknown | AxiosError) {
      if (err instanceof AxiosError && err.response) {
        console.log(err);
      }
    }
  };

  useEffectOnce(() => {
    fetchJobPosts();
  });

  return <Box>Joblet authenticated page</Box>;
};

export default Joblet;
