import { Box, Flex, Heading, Text } from '@chakra-ui/react';
import { AxiosError } from 'axios';
import { useContext, useState } from 'react';
import { Link as RouterLink, useParams } from 'react-router-dom';
import { UserContext } from '../context/user';
import { profileState } from '../data';
import { http } from '../helpers/utils';
import { useEffectOnce } from '../hooks/UseEffectOnce';
import { IUserContext } from '../interfaces';
import BasicInformation from '../components/Profile/BasicInformation';

const Profile = () => {
  const { user } = useContext(UserContext) as IUserContext;
  const { id: profileId } = useParams();
  const [profile, setProfile] = useState(profileState);
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

  useEffectOnce(() => {
    getProfile();
  });

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
      </Box>
      {/*<RouterLink to={`/profile/${user.profileId}/edit`}>Edit Profile</RouterLink>*/}
    </Box>
  );
};

export default Profile;
