import { Box, Flex, Text } from '@chakra-ui/react';
import { BsTelephoneFill, BsChevronRight } from 'react-icons/bs';
import { HiMail } from 'react-icons/hi';
import { MdLocationPin } from 'react-icons/md';
import { useNavigate } from 'react-router-dom';

interface IBasicInformationProps {
  email: string;
  phone: string;
  city: string;
  state: string;
  country: string;
  profileId: number;
}

const BasicInformation = ({
  email,
  profileId,
  phone,
  city,
  state,
  country,
}: IBasicInformationProps) => {
  const navigate = useNavigate();

  const handleNavigate = () => {
    navigate(`/profile/${profileId}/edit`);
  };

  return (
    <Box
      onClick={handleNavigate}
      _hover={{ boxShadow: 'rgba(100, 100, 111, 0.2) 0px 7px 29px 0px' }}
      bg="#f3f2f1"
      my="4rem"
      padding="1rem"
      borderRadius="8px"
      cursor="pointer"
    >
      <Flex color="black.primary" justifyContent="space-between" alignItems="center">
        <Box>
          <Flex my="0.5rem" alignItems="center">
            <HiMail />
            {email !== null ? (
              <Text ml="0.5rem">{email}</Text>
            ) : (
              <Text ml="0.5rem">Edit in profile</Text>
            )}
          </Flex>
          <Flex my="0.5rem" alignItems="center">
            <BsTelephoneFill />
            {phone !== null ? (
              <Text ml="0.5rem">{phone}</Text>
            ) : (
              <Text ml="0.5rem">Edit in profile</Text>
            )}
          </Flex>

          <Flex my="0.5rem" alignItems="center">
            <MdLocationPin />
            {city !== null || state !== null || country !== null ? (
              <Text ml="0.5rem">
                {city}, {state} {country}
              </Text>
            ) : (
              <Text ml="0.5rem">Edit in profile</Text>
            )}
          </Flex>
        </Box>
        <BsChevronRight />
      </Flex>
    </Box>
  );
};

export default BasicInformation;
