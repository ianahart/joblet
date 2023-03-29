import { Box, Button, Flex, Heading, Link, Text } from '@chakra-ui/react';
import { AxiosError } from 'axios';
import { useCallback, useContext, useEffect, useRef, useState } from 'react';
import { Link as RouterLink, useParams } from 'react-router-dom';
import { UserContext } from '../context/user';
import { profileState } from '../data';
import { http } from '../helpers/utils';
import { useEffectOnce } from '../hooks/UseEffectOnce';
import { IUserContext } from '../interfaces';
import BasicInformation from '../components/Profile/BasicInformation';
import FileUploader from '../components/Upload/FileUploader';
import { BsThreeDotsVertical } from 'react-icons/bs';
import ResumeOptions from '../components/Profile/ResumeOptions';
const Profile = () => {
  const { user } = useContext(UserContext) as IUserContext;
  const triggerRef = useRef<HTMLDivElement>(null);
  const menuRef = useRef<HTMLDivElement>(null);
  const { id: profileId } = useParams();
  const [profile, setProfile] = useState(profileState);
  const [file, setFile] = useState<File | null>(null);
  const [resumeOptionsOpen, setResumeOptionsOpen] = useState(false);
  const getProfile = async () => {
    try {
      const response = await http.get(`/profiles/${profileId}`);
      setProfile(response.data.profile);
    } catch (err: unknown | AxiosError) {
      if (err instanceof AxiosError && err.response) {
        console.log(err.response);
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

  const clickAway = useCallback(
    (e: MouseEvent) => {
      const target = e.target as Element;

      if (menuRef.current !== null && triggerRef.current !== null) {
        if (!menuRef.current.contains(target) && triggerRef.current !== target) {
          setResumeOptionsOpen(false);
        }
      }
    },
    [triggerRef, setResumeOptionsOpen]
  );

  const handleResumeOptionsClick = (e: React.MouseEvent<HTMLDivElement>) => {
    e.stopPropagation();
    setResumeOptionsOpen(true);
  };

  useEffect(() => {
    window.addEventListener('click', clickAway);
    return () => window.removeEventListener('click', clickAway);
  }, [clickAway]);

  return (
    <Box minH="100vh" display="flex" justifyContent="center">
      <Box width={['95%', '95%', '550px']} mt="5rem" p="0.5rem">
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
            <Box position="relative">
              {profile.resume && (
                <Box ref={triggerRef} cursor="pointer" onClick={handleResumeOptionsClick}>
                  <BsThreeDotsVertical />
                </Box>
              )}
              {resumeOptionsOpen && (
                <Box
                  ref={menuRef}
                  position="absolute"
                  right="0px"
                  top="30px"
                  minH="400px"
                  border="border 1px solid"
                  borderColor="border.primary"
                  minW="300px"
                  borderRadius="8px"
                  boxShadow="rgba(100, 100, 111, 0.2) 0px 7px 29px 0px"
                  bg="#fff"
                >
                  <ResumeOptions
                    setProfile={setProfile}
                    setFile={setFile}
                    resume={profile.resume}
                    profileId={profile.id}
                  />
                </Box>
              )}
            </Box>
          </Flex>
        </Box>
      </Box>
    </Box>
  );
};

export default Profile;
