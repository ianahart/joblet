import { Box, Button, Flex, Text } from '@chakra-ui/react';

interface IBasicModalProps {
  leftBtnText: string;
  rightBtnText: string;
  modalOpen: boolean;
  h: string;
  w: string[];
  text: string;
  setModalOpen: (modalOpen: boolean) => void;
  deleteResource: () => void;
}

const BasicModal = ({
  leftBtnText,
  rightBtnText,
  modalOpen,
  h,
  w,
  text,
  setModalOpen,
  deleteResource,
}: IBasicModalProps) => {
  const handleCloseModal = (e: React.MouseEvent<HTMLButtonElement>) => {
    e.stopPropagation();
    setModalOpen(false);
  };

  const handleDelete = (e: React.MouseEvent<HTMLButtonElement>) => {
    e.stopPropagation();
    deleteResource();
    setModalOpen(false);
  };

  return (
    <>
      {modalOpen && (
        <Flex
          flexDir="column"
          alignItems="center"
          justifyContent="center"
          position="absolute"
          top="0"
          left="0"
          zIndex="5"
          width="100%"
          height="100%"
          bg="rgba(0, 0, 0, 0.75)"
        >
          <Box width={w} minH={h} bg="#fff" borderRadius="8px">
            <Box bg="green.primary" height="40px" borderTopRadius="8px"></Box>
            <Flex textAlign="center" mt="1rem" p="0.25rem">
              <Text color="black.primary">{text}</Text>
            </Flex>
            <Flex justifyContent="space-evenly" my="1.5rem">
              <Button onClick={handleDelete}>{leftBtnText}</Button>
              <Button onClick={handleCloseModal} colorScheme="teal">
                {rightBtnText}
              </Button>
            </Flex>
          </Box>
        </Flex>
      )}
    </>
  );
};

export default BasicModal;
