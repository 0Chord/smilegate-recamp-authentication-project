import {useState} from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";

export default function Login() {
    const navigator = useNavigate();

    const [Email, setEmail] = useState("");
    const [Password, setPassword] = useState("");
    const apiAddr = `https://muxplorer.shop`;

    const onEmailHandler = (event) => {
        setEmail(event.currentTarget.value);
    }
    const onPasswordHandler = (event) => {
        setPassword(event.currentTarget.value);
    }

    const onLoginHandler = (event) => {
        event.preventDefault();
        let data = {
            email: Email,
            password: Password
        }
        console.log(data)
        axios.post(apiAddr + `/api/v1/verified/login`, data)
            .then(response => {
                if (response.data === "SUCCESS") {
                    console.log(response.headers['access-token'])
                    const accessToken = response.headers.get("access-token");
                    const refreshToken = response.headers.get("refresh-token");
                    const userId = response.headers.get("user-id");
                    localStorage.clear();
                    localStorage.setItem("access-token", accessToken);
                    localStorage.setItem("refresh-token", refreshToken);
                    localStorage.setItem("user-id", userId);
                    navigator("/");
                }
            })
            .catch(error => {
                console.error(error.response.data.message);
                alert(error.response.data.message);
                window.location.reload();
            })
    }

    return (
        <div className="flex items-center justify-center min-h-screen bg-[#5865F2]">
            <div className="w-full max-w-md p-8 space-y-3 bg-black rounded-lg shadow-md">
                <h1 className="text-2xl font-bold text-white">로그인에 오신 것을 환영해요!</h1>
                <p className="text-sm text-gray-400">다시 만나다니 너무 반가워요!</p>
                <form className="space-y-4">
                    <div>
                        <label className="block text-sm font-medium text-gray-400" htmlFor="email">
                            이메일 *
                        </label>
                        <input
                            className="w-full px-3 py-2 mt-1 text-black placeholder-gray-400 bg-white border rounded-md focus:border-blue-500 focus:ring focus:ring-blue-200 focus:outline-none"
                            id="email"
                            placeholder="이메일 또는 전화번호를 입력해주세요"
                            required
                            type="email"
                            onChange={onEmailHandler}
                        />
                    </div>
                    <div>
                        <label className="block text-sm font-medium text-gray-400" htmlFor="password">
                            비밀번호 *
                        </label>
                        <input
                            className="w-full px-3 py-2 mt-1 text-black placeholder-gray-400 bg-white border rounded-md focus:border-blue-500 focus:ring focus:ring-blue-200 focus:outline-none"
                            id="password"
                            placeholder="비밀번호를 입력해주세요"
                            required
                            type="password"
                            onChange={onPasswordHandler}
                        />
                    </div>
                    <div className="flex items-center justify-between">
                        <div className="flex items-center">
                            <input
                                className="w-4 h-4 text-blue-600 border-gray-300 rounded focus:ring-blue-500"
                                id="remember"
                                type="checkbox"
                            />
                            <label className="block ml-2 text-sm text-gray-400" htmlFor="remember">
                                비밀번호를 잊으셨나요?
                            </label>
                        </div>
                        <button
                            className="px-4 py-2 text-sm font-medium text-white bg-blue-600 rounded-md hover:bg-blue-700 focus:outline-none focus:ring focus:ring-blue-300"
                            type="submit" onClick={onLoginHandler}>
                            로그인
                        </button>
                    </div>
                </form>
                <p className="text-xs text-gray-400">
                    계정이 필요하신가요?{" "}
                    <a className="text-blue-300 underline" href="/signup">
                        가입하기
                    </a>
                </p>
            </div>
        </div>
    )
}