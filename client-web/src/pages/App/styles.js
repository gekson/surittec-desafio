import styled from "styled-components";
import { darken } from "polished";

export const Container = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100vh;
`;

export const Form = styled.form`
width: 400px;
background: #fff;
padding: 20px;
display: flex;
flex-direction: column;
align-items: center;
img {
  width: 100px;
  margin: 10px 0 40px;
}
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
  color: #777;
  font-size: 15px;
  width: 100%;
  border: 1px solid #ddd;
  &::placeholder {
    color: #999;
  }
}
button {
  color: #fff;
  font-size: 16px;
  background: #fc6963;
  height: 56px;
  border: 0;
  border-radius: 5px;
  width: 100%;
}
hr {
  margin: 20px 0;
  border: none;
  border-bottom: 1px solid #cdcdcd;
  width: 100%;
}
a {
  font-size: 16;
  font-weight: bold;
  color: #999;
  text-decoration: none;
}
`;

export const ButtonContainer = styled.div`
  position: absolute;
  bottom: 20px;
  right: 10px;
  display: flex;
  flex-direction: column;
`;

export const NewButtonContainer = styled.div`
position: absolute;
bottom: 54;
left: 20;
right: 20;
alignSelf: center;
borderRadius: 5px;
paddingVertical: 20px;
backgroundColor: #fc6663;
`;

export const ButtonText = styled(Text)`
color: #fff;
fontSize: 16px;
textAlign: center;
fontWeight: bold;
`;

export const PointReference = styled.div`
position: absolute;
top: 0;
width: 100vw;
height: 100vh;
pointer-events: none;
display: flex;
align-items: center;
justify-content: center;
flex-direction: column;
i {
  color: #fc6963;
  pointer-events: all;
  font-size: 50px;
  margin-top: 112px;
  margin-left: 12px;
  -webkit-text-fill-color: #fc6963;
  -webkit-text-stroke-width: 2px;
  -webkit-text-stroke-color: ${() => darken(0.05, "#fc6963")};
}
div {
  margin-top: 100px;
  button {
    border: none;
    font-size: 15px;
    height: 46px;
    margin: 0 10px;
    background-color: #fc6963;
    color: #ffffff;
    padding: 0 20px;
    border-radius: 3px;
    pointer-events: all;
    text-align: center;
    &.cancel {
      background: #ffffff;
      color: #333333;
    }
  }
}
`;