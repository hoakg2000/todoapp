import React, { useState } from "react";
import { Button, Checkbox, Form, Input } from 'antd';
import { useHistory } from 'react-router-dom';
import { useAuth } from "../../context/AuthProvider";
import { requestClient } from "../../api/requestclient";

function Login() {

    const { login } = useAuth();
    const [message, setMessage] = useState<string>("");
    const history = useHistory();

    var [isLoading, setIsLoading] = useState(false);
    var [error, setError] = useState(null);
    var [activeTabId, setActiveTabId] = useState(0);
    var [username, setUsername] = useState("admin@flatlogic.com");
    var [passwordValue, setPasswordValue] = useState("password");

    const handleLogin = (username: string, password: string) => {
        login(username, password).then((data: any) => {
            // history.push('/app/dashboard')
            console.log(data)
        }).catch((error) => {
            console.log(error);
        })
        requestClient.get("/api/event/test").then((data: any) => {
            console.log(data);
        }).catch((error) => {
            console.log(error);
        })
    }
    const onFinish = (values: any) => {
        console.log('Success:', values);
        handleLogin(values.username, values.password)
    };

    const onFinishFailed = (errorInfo: any) => {
        console.log('Failed:', errorInfo);
    };

    type FieldType = {
        username?: string;
        password?: string;
    };

    return (
        <Form
            name="basic"
            labelCol={{ span: 8 }}
            wrapperCol={{ span: 16 }}
            style={{ maxWidth: 600, padding: '1rem', margin: "auto" }}
            initialValues={{ remember: true }}
            onFinish={onFinish}
            onFinishFailed={onFinishFailed}
            autoComplete="off"
        >
            <Form.Item<FieldType>
                label="Username"
                name="username"
                rules={[{ required: true, message: 'Please input your username!' }]}
            >
                <Input />
            </Form.Item>

            <Form.Item<FieldType>
                label="Password"
                name="password"
                rules={[{ required: true, message: 'Please input your password!' }]}
            >
                <Input.Password />
            </Form.Item>

            <Form.Item wrapperCol={{ offset: 8, span: 16 }}>
                <Button type="primary" htmlType="submit">
                    Submit
                </Button>
            </Form.Item>
        </Form>
    );
}

export default Login;
