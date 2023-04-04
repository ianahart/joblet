import { Box, Flex, Text } from '@chakra-ui/react';
import { AiOutlineClose } from 'react-icons/ai';
import { ISearch } from '../../interfaces';

interface ISearchesProps {
  searches: ISearch[];
  research: (value: string) => void;
  removeSearch: (id: number) => void;
}

const Searches = ({ searches, research, removeSearch }: ISearchesProps) => {
  const handleOnClick = (id: number) => {
    removeSearch(id);
  };

  return (
    <Box
      width={['95%', '95%', '590px']}
      border="1px solid"
      borderColor="border.primary"
      minH="500px"
      borderRadius="8px"
      mx="auto"
    >
      <Box>
        {searches.map((search) => {
          return (
            <Flex
              alignItems="center"
              padding="0.5rem"
              justifyContent="space-between"
              borderBottom="1px solid"
              borderColor="border.primary"
              key={search.id}
            >
              <Text
                role="button"
                onClick={() => research(search.term)}
                color="text.primary"
                cursor="pointer"
              >
                {search.term}
              </Text>
              <Box
                onClick={() => handleOnClick(search.id)}
                cursor="pointer"
                fontSize="1.2rem"
                color="text.primary"
              >
                <AiOutlineClose />
              </Box>
            </Flex>
          );
        })}
      </Box>
    </Box>
  );
};

export default Searches;
