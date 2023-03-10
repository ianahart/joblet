import { Box, Text } from '@chakra-ui/react';
import { useRef, useState, useEffect, useCallback } from 'react';
import { useEffectOnce } from '../../hooks/UseEffectOnce';
import { BsChevronDown } from 'react-icons/bs';
import { IDropdownData } from '../../interfaces';

interface IFormDropdownProps {
  updateField: (name: string, value: string, attribute: string) => void;
  data: { name: string; id: number }[];
  label: string;
  name: string;
}

const FormDropdown = ({ updateField, data, label, name }: IFormDropdownProps) => {
  const [isDropdownOpen, setIsDropdownOpen] = useState(false);
  const [selected, setSelected] = useState<IDropdownData>({ name: '', id: 0 });
  const menuRef = useRef<HTMLDivElement>(null);
  const triggerRef = useRef<HTMLDivElement>(null);

  useEffectOnce(() => {
    if (data.length) {
      setSelected(data[0]);
    }
  });

  const handleOnSelected = (
    e: React.MouseEvent<HTMLParagraphElement>,
    data: IDropdownData
  ) => {
    e.stopPropagation();
    setSelected(data);
    setIsDropdownOpen(false);
    updateField(name, data.name, 'value');
  };

  const clickAway = useCallback(
    (e: MouseEvent) => {
      const target = e.target as Element;
      if (menuRef.current !== null && triggerRef.current !== null) {
        if (!menuRef.current.contains(target) && !triggerRef.current.contains(target)) {
          setIsDropdownOpen(false);
        }
      }
    },
    [setIsDropdownOpen, triggerRef]
  );

  useEffect(() => {
    window.addEventListener('click', clickAway);
    return () => window.removeEventListener('click', clickAway);
  }, [clickAway]);

  return (
    <Box width="80%">
      <Text color="text.primary" my="0.25rem">
        {label}
      </Text>
      <Box
        ref={triggerRef}
        onClick={() => setIsDropdownOpen(true)}
        position="relative"
        padding="0.5rem"
        display="flex"
        alignItems="center"
        justifyContent="space-between"
        border="1px solid #e2e8f0"
        height="40px"
        borderRadius="8px"
        width="100%"
      >
        <Text color="text.primary">{selected.name}</Text>
        <Box color="#e2e8f0">
          <BsChevronDown />
        </Box>
        {isDropdownOpen && (
          <Box
            ref={menuRef}
            className="overflow-scroll"
            position="absolute"
            bg="#fff"
            zIndex="2"
            top="45px"
            padding="0.5rem"
            border="1px solid #e2e8f0"
            borderRadius="8px"
            width="100%"
            overflowY="auto"
            height="300px"
          >
            {data.map((item) => {
              return (
                <Text
                  role="button"
                  _hover={{ background: '#57cc99', opacity: 0.4 }}
                  cursor="pointer"
                  p="0.5rem"
                  onClick={(e) => handleOnSelected(e, item)}
                  key={item.id}
                >
                  {item.name}
                </Text>
              );
            })}
          </Box>
        )}
      </Box>
    </Box>
  );
};

export default FormDropdown;
