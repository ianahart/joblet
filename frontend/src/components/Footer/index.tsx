import { Flex, Text } from '@chakra-ui/react';

const Footer = () => {
  return (
    <Flex justifyContent="center" p="0.5rem" className="footer" as="footer">
      <Text color="text.primary">All Rights Reserved &copy; joblet 2023</Text>
    </Flex>
  );
};

export default Footer;
