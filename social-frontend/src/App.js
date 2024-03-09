import { Routes, Route } from "react-router-dom";
import "./App.css";
import Authentication from "./pages/Authentication/Authentication";
import HomePage from "./pages/HomePage/HomePage";
import Message from "./pages/Message/Message";
import { useSelector,useDispatch } from "react-redux";
import { useEffect } from "react";
import { getProfileAction } from "./Redux/Auth/auth.action";
import { ThemeProvider } from "@emotion/react";
import { darkTheme } from "./theme/DarkTheme";

function App() {
  const user = useSelector((store) => store.auth.user);
  const dispatch = useDispatch();
  const jwt = localStorage.getItem("jwt");
  useEffect(() => {
    dispatch(getProfileAction(jwt));
  }, [jwt,dispatch]);

  return (
    <ThemeProvider theme={darkTheme}>
      <Routes>
        <Route
          path="/*"
          element={user ? <HomePage /> : <Authentication />}
        />
        <Route path="/message" element={<Message />} />
        <Route path="/*" element={<Authentication />} />
      </Routes>
    </ThemeProvider>
  );
}

export default App;
