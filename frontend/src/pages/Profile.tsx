import { Box, Button, Flex, Heading, Link, Text } from '@chakra-ui/react';
import { AxiosError } from 'axios';
import { useContext, useState } from 'react';
import { Link as RouterLink, useParams } from 'react-router-dom';
import { UserContext } from '../context/user';
import { profileState } from '../data';
import { http } from '../helpers/utils';
import { useEffectOnce } from '../hooks/UseEffectOnce';
import { IUserContext } from '../interfaces';
import BasicInformation from '../components/Profile/BasicInformation';
import FileUploader from '../components/Upload/FileUploader';
import { BsThreeDotsVertical } from 'react-icons/bs';
const Profile = () => {
  const { user } = useContext(UserContext) as IUserContext;
  const { id: profileId } = useParams();
  const [profile, setProfile] = useState(profileState);
  const [file, setFile] = useState<File | null>(null);
  const [downloadUrl, setDownloadUrl] = useState('');
  const getProfile = async () => {
    try {
      const response = await http.get(`/profiles/${profileId}`);
      setProfile(response.data.profile);
    } catch (err: unknown | AxiosError) {
      if (err instanceof AxiosError && err.response) {
        return;
      }
    }
  };

  const handleFileUpload = async (file: File) => {
    try {
      const formData = new FormData();
      formData.append('file', file);
      const response = await http.patch(`/profiles/upload/${profile.id}`, formData);
      setFile(file);
    } catch (err: unknown | AxiosError) {
      if (err instanceof AxiosError && err.response) {
        console.log(err);
      }
    }
  };

  useEffectOnce(() => {
    getProfile();
  });

  const getFileName = () => {
    if (file === null && profile.fileName === null) {
      return '';
    } else if (file !== null) {
      return file.name;
    } else {
      return profile.fileName;
    }
  };

  const downloadFile = async () => {
    try {
      const response = await http.get(`/profiles/download/${profile.id}`, {
        responseType: 'blob',
      });

      const fileURL = window.URL.createObjectURL(response.data);

      // Setting various property values
      let alink = document.createElement('a');
      alink.href = fileURL;
      alink.download = 'SamplePDF.pdf';
      alink.click();
    } catch (err: unknown | AxiosError) {
      if (err instanceof AxiosError && err.response) {
        console.log(err.response);
      }
    }
  };

  return (
    <Box minH="100vh" display="flex" justifyContent="center">
      <Box width={['95%', '95%', '550px']} mt="5rem" p="0.5rem" border="1px solid blue">
        <Flex justifyContent="space-between" alignItems="center">
          <Heading as="h1" color="black.primary">
            {user.firstName} {user.lastName}
          </Heading>
          <Flex
            flexDir="column"
            justifyContent="center"
            alignItems="center"
            borderRadius="50%"
            width="60px"
            height="60px"
            bg="black.primary"
          >
            <Text fontWeight="bold" fontSize="1.5rem" color="#fff">
              {user.abbreviation}
            </Text>
          </Flex>
        </Flex>

        <BasicInformation
          email={profile.email}
          phone={profile.phoneNumber}
          city={profile.city}
          country={profile.country}
          state={profile.state}
          profileId={profile.id}
        />
        <Box>
          <Heading fontSize="1.5rem" color="black.primary">
            Resume
          </Heading>
          <Flex
            border="1px solid"
            borderColor="border.primary"
            borderRadius="8px"
            minH="100px"
            alignItems="center"
            p="1rem"
            justifyContent="space-between"
          >
            <FileUploader handleFileUpload={handleFileUpload} fileName={getFileName()} />
            <Box>
              <BsThreeDotsVertical />
            </Box>
          </Flex>
        </Box>
      </Box>
    </Box>
  );
};

export default Profile;
