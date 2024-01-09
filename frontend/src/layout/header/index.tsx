// Header.js
import React from 'react';
import { Layout, Menu } from 'antd';
import {
    HomeOutlined,
    UserOutlined,
    SettingOutlined,
} from '@ant-design/icons';
import { Link } from 'react-router-dom';

const Header = () => {
    return (
        <Layout.Header >
            <div className="logo" />
            <Menu theme="dark" mode="horizontal" defaultSelectedKeys={['1']} style={{ display: 'flex', justifyContent: 'center' }}>

                <Menu.Item key="1" icon={<HomeOutlined />}>
                    <Link to="/home">Home</Link>
                </Menu.Item>
                <Menu.Item key="3" icon={<SettingOutlined />}>
                    <Link to="/settings">Settings</Link>
                </Menu.Item>
                <Menu.Item key="2" icon={<UserOutlined />} >
                    <Link to="/profile">Profile</Link>
                </Menu.Item>
            </Menu>
        </Layout.Header>
    );
};

export default Header;
