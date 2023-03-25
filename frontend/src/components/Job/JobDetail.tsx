import { Box, Flex, Text } from '@chakra-ui/react';

interface IJobDetailProps {
  iconOne: JSX.Element;
  iconTwo: JSX.Element | undefined;
  title: string;
  value: string | number;
  bg: string;
  color: string;
}

const JobDetail = ({ bg, iconOne, color, iconTwo, title, value }: IJobDetailProps) => {
  return (
    <Box>
      <Flex color="text.secondary" alignItems="center">
        {iconOne}
        <Text mx="1rem" fontWeight="bold">
          {title}
        </Text>
      </Flex>
      <Flex mt="0.5rem" flexDir="column" alignItems="flex-start">
        <Box
          p="0.25rem"
          borderRadius="8px"
          color={color}
          fontWeight="bold"
          bg={bg}
          ml="2rem"
          display="flex"
          alignItems="center"
        >
          <Text mr="0.5rem">{value}</Text>
          {iconTwo && iconTwo}
        </Box>
      </Flex>
    </Box>
  );
};

export default JobDetail;
