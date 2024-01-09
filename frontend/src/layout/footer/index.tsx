// App.js
import React from 'react';
import { Layout } from 'antd';

function Footer() {
    return (
        <Layout.Footer style={{
            textAlign: 'center',
            backgroundColor: '#001529',
            color: '#fff',
            padding: '20px 0',
            position: 'fixed', bottom: 0, width: '100%'
        }}>
            Ant Design ©2022 Created by Ant UED
        </Layout.Footer>
    );
}

export default Footer;
