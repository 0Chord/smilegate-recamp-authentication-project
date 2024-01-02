import {useEffect, useState} from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";

export default function Private() {

    const [Email, setEmail] = useState("");
    const [Name, setName] = useState("");
    const [Image, setImage] = useState("");
    const [Phone, setPhone] = useState("");
    const [Password, setPassword] = useState("");
    const [newImage, setNewImage] = useState(null);

    const userId = localStorage.getItem("user-id");
    const accessToken = localStorage.getItem("access-token");
    const bearerToken = "Bearer " + accessToken;
    const navigator = useNavigate();
    const apiAddr = `https://muxplorer.shop`;


    const onPasswordHandler = (event) => {
        setPassword(event.currentTarget.value);
    }

    const onNewImageHandler = (event) => {
        setNewImage(event.target.files[0]);
    }
    useEffect(() => {

        axios.get(apiAddr + `/api/v1/user/${userId}/get-profile`, {
            headers: {
                "Authorization": bearerToken
            }
        })
            .then(response => {
                setEmail(response.data.email);
                setName(response.data.name);
                setImage(response.data.image);
                setPhone(response.data.phone);
            })
            .catch(error => {
                console.error(error);
                alert("다시 로그인을 해주시길 바랍니다");
                localStorage.clear();
                navigator("/login");
            })
    }, []);

    const onPasswordSubmitHandler = (event) => {
        event.preventDefault();
        let data = {
            password: Password
        }
        axios.post(apiAddr + `/api/v1/user/${userId}/change/password`, data, {
            headers: {
                "Authorization": bearerToken
            }
        })
            .then(response => {
                if (response.data === "SUCCESS") {
                    alert("비밀번호 변경에 성공했습니다. 다시 로그인 해주시길 바랍니다");
                    localStorage.clear();
                    navigator("/login");
                }
            })
            .catch(error => {
                console.error(error.response.data.message);
                alert(error.response.data.message);
                window.location.reload();
            })
    }
    const onNewImageSubmitHandler = (event) => {
        event.preventDefault();

        let formData = new FormData();
        formData.append("image", newImage);
        axios.patch(apiAddr + `/api/v1/user/${userId}/change/profile-image`, formData, {
            headers: {
                "Authorization": bearerToken
            }
        })
            .then(response => {
                if (response.data === "SUCCESS") {
                    alert("프로필 이미지 변경에 성공했습니다");
                    window.location.reload();
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
                <h1 className="text-2xl font-bold text-white">프로필 정보</h1>
                <p className="text-sm text-gray-400">정보를 업데이트하거나 확인하세요.</p>
                <form className="space-y-4">
                    <div className="w-[100px] h-[100px]">
                        <p className="mt-2 text-sm text-gray-400">프로필 사진</p>
                        <img
                            alt="프로필 사진"
                            className="w-20 h-20 bg-white rounded-full"
                            height="20"
                            src={Image}
                            width="20"
                        />
                    </div>
                    <div>
                        <label className="block text-sm font-medium text-gray-400" htmlFor="email">
                            이메일
                        </label>
                        <input
                            className="bg-[#333333] w-full px-3 py-2 mt-1 border rounded-md focus:border-blue-500 focus:ring focus:ring-blue-200 focus:outline-none text-white"
                            id="email"
                            value={Email}
                            required
                            readOnly={true}
                        />
                    </div>
                    <div>
                        <label className="block text-sm font-medium text-gray-400" htmlFor="phone">
                            전화번호
                        </label>
                        <input
                            className="w-full px-3 py-2 mt-1 text-white bg-[#333333] border rounded-md focus:border-blue-500 focus:ring focus:ring-blue-200 focus:outline-none"
                            id="phone"
                            required
                            type="tel"
                            value={Phone}
                            readOnly={true}
                        />
                    </div>
                    <div>
                        <label className="block text-sm font-medium text-gray-400" htmlFor="name">
                            이름
                        </label>
                        <input
                            className="w-full px-3 py-2 mt-1 text-white bg-[#333333] border rounded-md focus:border-blue-500 focus:ring focus:ring-blue-200 focus:outline-none"
                            id="name"
                            required
                            value={Name}
                            type="text"
                            readOnly={true}
                        />
                    </div>
                    <div>
                        <label className="block text-sm font-medium text-gray-400" htmlFor="password">
                            비밀번호 변경
                        </label>
                        <div className="mt-2">
                            <input
                                className="w-full px-3 py-2 mt-1 text-white placeholder-gray-400 bg-[#333333] border rounded-md focus:border-blue-500 focus:ring focus:ring-blue-200 focus:outline-none"
                                id="password"
                                placeholder="새 비밀번호를 입력하세요"
                                required
                                type="password"
                                onChange={onPasswordHandler}
                            />
                            <button
                                className="mt-2 px-4 py-2 text-sm font-medium text-white bg-[#5865F2] rounded-md hover:bg-blue-700 focus:outline-none focus:ring focus:ring-blue-300"
                                type="button"
                                onClick={onPasswordSubmitHandler}
                            >
                                비밀번호 번경
                            </button>
                        </div>
                    </div>
                    <div>
                        <label className="block text-sm font-medium text-gray-400" htmlFor="image">
                            프로필 이미지 변경
                        </label>
                        <div className="mt-2">
                            <input
                                className="block w-full text-sm text-slate-500
        file:mr-4 file:py-2 file:px-4 file:rounded-md file:border-0 file:text-sm file:font-semibold
        file:bg-[#5865F2] file:text-white hover:file:bg-pink-100" accept=".png, .jpeg, .jpg" id="photo" type="file"
                                required
                                onChange={onNewImageHandler}
                            />
                            <button
                                className="mt-2 px-4 py-2 text-sm font-medium text-white bg-[#5865F2] rounded-md hover:bg-blue-700 focus:outline-none focus:ring focus:ring-blue-300"
                                type="button"
                                onClick={onNewImageSubmitHandler}>
                                프로필 이미지 변경
                            </button>
                        </div>
                    </div>
                    <p className="text-sm text-center text-white">
                        관리자 이십니까?{" "}
                        <a className="text-[#5865F2]" href="/admin/page">
                            관리자 페이지 이동
                        </a>
                    </p>
                </form>

            </div>
        </div>
    )
}
