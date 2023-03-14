import { Box, Text } from '@chakra-ui/react';
import { AxiosError } from 'axios';
import { AiOutlineDownload } from 'react-icons/ai';
import { BsBoxArrowInUpRight, BsTrash } from 'react-icons/bs';
import { Link as RouterLink } from 'react-router-dom';
import { http } from '../../helpers/utils';
import { IProfile } from '../../interfaces';

interface IResumeOptionsProps {
  setProfile: (profile: any) => void;
  setFile: (file: File | null) => void;
  profileId: number;
  resume: string;
}

const ResumeOptions = ({
  setProfile,
  setFile,
  profileId,
  resume,
}: IResumeOptionsProps) => {
  const downloadFile = async (e: React.MouseEvent<HTMLDivElement>) => {
    try {
      e.stopPropagation();
      const response = await http.get(`/profiles/download/${profileId}`, {
        responseType: 'blob',
      });
      const fileURL = window.URL.createObjectURL(response.data);
      let alink = document.createElement('a');
      alink.href = fileURL;
      alink.download = 'resumePDF.pdf';
      alink.click();
    } catch (err: unknown | AxiosError) {
      if (err instanceof AxiosError && err.response) {
        console.log(err.response);
      }
    }
  };

  const deleteFile = async (e: React.MouseEvent<HTMLDivElement>) => {
    try {
      e.stopPropagation();
      const response = await http.patch(`/profiles/resume/${profileId}`, {
        fileName: null,
        resume: null,
      });
      setFile(null);
      setProfile((prevState: any) => ({ ...prevState, fileName: '' }));
    } catch (err: unknown | AxiosError) {
      if (err instanceof AxiosError && err.response) {
        console.log(err);
      }
    }
  };

  return (
    <Box>
      <Box
        cursor="pointer"
        _hover={{ opacity: 0.4, backgroundColor: '#57cc99' }}
        my="1.5rem"
        fontSize="1.5rem"
        p="1rem"
        display="flex"
        alignItems="center"
      >
        <BsBoxArrowInUpRight />
        <Box fontSize="1.1rem" ml="0.5rem">
          <RouterLink to="/document-view" state={{ resume }}>
            View
          </RouterLink>
        </Box>
      </Box>

      <Box
        onClick={downloadFile}
        cursor="pointer"
        _hover={{ opacity: 0.4, backgroundColor: '#57cc99' }}
        my="1.5rem"
        fontSize="1.5rem"
        p="1rem"
        display="flex"
        alignItems="center"
      >
        <AiOutlineDownload />
        <Text fontSize="1.1rem" ml="0.5rem">
          Download
        </Text>
      </Box>

      <Box
        onClick={deleteFile}
        cursor="pointer"
        _hover={{ opacity: 0.4, backgroundColor: '#57cc99' }}
        my="1.5rem"
        fontSize="1.5rem"
        p="1rem"
        display="flex"
        alignItems="center"
      >
        <BsTrash />
        <Text fontSize="1.1rem" ml="0.5rem">
          Delete
        </Text>
      </Box>
    </Box>
  );
};

export default ResumeOptions;
