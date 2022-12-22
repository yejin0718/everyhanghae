//데이터 입력 형택 hook

const useDate = () => {
  //createDate함수 만들기
  let currentDate = new Date();
  let year = currentDate.getFullYear();
  let month = currentDate.getMonth() + 1;
  let day = currentDate.getDate();
  let date = year + "-" + month + "-" + day;

  return date;
};

export default useDate;
