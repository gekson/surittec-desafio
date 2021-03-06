import styled from "styled-components";

export const Form = styled.form`
display: flex;
flex-direction: column;
align-items: center;
padding: 10px 20px;
height: 600px;
width: 600px;
p {
  color: #ff3333;
  margin-bottom: 15px;
  border: 1px solid #ff3333;
  padding: 10px;
  width: 100%;
  text-align: center;
}
input {
  flex: 1;
  height: 46px;
  margin-bottom: 15px;
  padding: 0 20px;
  color: #777777;
  font-size: 15px;
  width: 100%;
  border: 1px solid #cccccc;
  &::placeholder {
    color: #999999;
  }
}
hr {
  margin: 20px 0;
  border: none;
  border-bottom: 1px solid #cdcdcd;
  width: 100%;
}
a {
  font-size: 16px;
  font-weight: bold;
  color: #999999;
  text-decoration: none;
}
div.actions {
  display: flex;
  margin-top: 15px;
  justify-content: space-between;
  button {
    color: #ffffff;
    font-size: 16px;
    background: #fc6963;
    height: 56px;
    border: 0;
    border-radius: 5px;
    padding: 0 30px;
    &.cancel {
      background: #222222;
    }
  }
}
`;