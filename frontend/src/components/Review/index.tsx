import { Box, Flex, Text } from '@chakra-ui/react';
import { IReview, IUserContext } from '../../interfaces';
import { FaUserCircle } from 'react-icons/fa';
import { AiFillStar } from 'react-icons/ai';
import { BsThreeDotsVertical, BsTrash } from 'react-icons/bs';
import { useContext, useEffect, useRef, useState, useCallback } from 'react';
import { UserContext } from '../../context/user';
import { AxiosError } from 'axios';
import { http } from '../../helpers/utils';
interface IReviewProps {
  review: IReview;
  deleteReviewItem: (id: number) => void;
}

const Review = ({ review, deleteReviewItem }: IReviewProps) => {
  const { user } = useContext(UserContext) as IUserContext;
  const [isPopUpShowing, setIsPopUpShowing] = useState(false);

  const menuRef = useRef<HTMLDivElement>(null);
  const triggerRef = useRef<HTMLDivElement>(null);

  const showPopUp = (e: React.MouseEvent<HTMLDivElement>) => {
    e.stopPropagation();
    setIsPopUpShowing(true);
  };

  const deleteReview = async (e: React.MouseEvent<HTMLDivElement>) => {
    try {
      e.stopPropagation();
      setIsPopUpShowing(false);

      const response = await http.delete(`/reviews/${review.id}`);
      deleteReviewItem(review.id);
    } catch (err: unknown | AxiosError) {
      if (err instanceof AxiosError && err.response) {
        console.log(err.response);
      }
    }
  };

  const clickAway = useCallback(
    (e: MouseEvent) => {
      const target = e.target as Element;
      if (menuRef.current !== null && triggerRef.current !== null) {
        if (!menuRef.current.contains(target) && !triggerRef.current.contains(target)) {
          setIsPopUpShowing(false);
        }
      }
    },
    [setIsPopUpShowing, triggerRef]
  );

  useEffect(() => {
    window.addEventListener('click', clickAway);
    return () => window.removeEventListener('click', clickAway);
  }, [clickAway]);

  return (
    <Box
      my="1rem"
      width={['95%', '95%', '400px']}
      border="1px solid"
      borderColor="border.primary"
      p="0.5rem"
      borderRadius="8px"
    >
      {user.id === review.userId && (
        <Box
          position="relative"
          cursor="pointer"
          display="flex"
          justifyContent="flex-end"
        >
          <Box ref={triggerRef} onClick={showPopUp}>
            <BsThreeDotsVertical />
            {isPopUpShowing && (
              <Box
                ref={menuRef}
                onClick={deleteReview}
                position="absolute"
                top="20px"
                right="0"
                padding="0.5rem"
                display="flex"
                alignItems="center"
                border="1px solid"
                borderColor="border.primary"
                borderRadius="8px"
              >
                <BsTrash />
                <Text fontSize="0.75rem">Delete</Text>
              </Box>
            )}
          </Box>
        </Box>
      )}
      <Flex alignItems="center">
        <Box mr="0.5rem" fontSize="1.5rem" color="text.primary">
          <FaUserCircle />
        </Box>
        <Box>
          <Text>
            {review.firstName} {review.lastName}
          </Text>
        </Box>
      </Flex>
      <Box mt="1rem">{review.companyName}</Box>
      <Flex my="1.5rem">
        {[...Array(5)].map((star, index) => {
          return (
            <Box>
              <AiFillStar color={review.rating > index ? 'orange' : 'black'} />
            </Box>
          );
        })}
      </Flex>
      <Box>
        <Text wordBreak="break-all">{review.text}</Text>
      </Box>
    </Box>
  );
};

export default Review;
