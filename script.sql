USE [WebGauBong]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 1/19/2021 12:47:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[Id] [nvarchar](10) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[OrderDetails]    Script Date: 1/19/2021 12:47:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetails](
	[Id] [int] NOT NULL,
	[Quantity] [int] NOT NULL,
	[UnitPrice] [float] NOT NULL,
	[ProId] [int] NOT NULL,
	[OrderId] [int] NOT NULL,
 CONSTRAINT [PK_OrderDetails] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Orders]    Script Date: 1/19/2021 12:47:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[Id] [int] NOT NULL,
	[UserId] [int] NOT NULL,
	[Created] [date] NOT NULL,
	[StatusId] [int] NOT NULL,
 CONSTRAINT [PK_Orders] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Products]    Script Date: 1/19/2021 12:47:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Products](
	[Id] [int] NOT NULL,
	[Name] [nvarchar](80) NOT NULL,
	[Price] [float] NOT NULL,
	[Photo] [nvarchar](500) NOT NULL,
	[Description] [nvarchar](500) NOT NULL,
	[Discount] [float] NULL,
	[Quantity] [int] NOT NULL,
	[Created] [date] NOT NULL,
	[CateId] [nvarchar](10) NOT NULL,
 CONSTRAINT [PK_Products] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Status]    Script Date: 1/19/2021 12:47:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Status](
	[Id] [int] NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Status] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Users]    Script Date: 1/19/2021 12:47:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[Id] [int] NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[Gender] [bit] NULL,
	[Username] [nvarchar](50) NOT NULL,
	[Password] [nvarchar](50) NOT NULL,
	[Email] [nvarchar](50) NOT NULL,
	[Phone] [nvarchar](50) NOT NULL,
	[Address] [nvarchar](200) NOT NULL,
	[Created] [date] NOT NULL,
	[UserRole] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Users] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[Category] ([Id], [Name]) VALUES (N'Gb', N'Gấu Bông')
INSERT [dbo].[Category] ([Id], [Name]) VALUES (N'GT', N'Gối Tựa')
INSERT [dbo].[OrderDetails] ([Id], [Quantity], [UnitPrice], [ProId], [OrderId]) VALUES (1, 2, 660, 1, 1)
INSERT [dbo].[Orders] ([Id], [UserId], [Created], [StatusId]) VALUES (1, 2, CAST(0x02420B00 AS Date), 1)
INSERT [dbo].[Products] ([Id], [Name], [Price], [Photo], [Description], [Discount], [Quantity], [Created], [CateId]) VALUES (1, N'Unicorn', 330, N'GbUnicorn.jpg', N'Gấu bông Unicorn nằm 55cm', 0, 10, CAST(0x0D410B00 AS Date), N'Gb')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Photo], [Description], [Discount], [Quantity], [Created], [CateId]) VALUES (2, N'Bò', 130, N'GbBo.jpg', N'Gấu bông Bò cute heart smile ', 0, 50, CAST(0x0D410B00 AS Date), N'Gb')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Photo], [Description], [Discount], [Quantity], [Created], [CateId]) VALUES (3, N'Gấu trúc', 190, N'GtGautruc.jpg', N'Gấu bông tựa lưng We bare bears cute gấu trúc ', 0, 20, CAST(0x0D410B00 AS Date), N'Gt')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Photo], [Description], [Discount], [Quantity], [Created], [CateId]) VALUES (4, N'Voi', 280, N'GtVoi.jpg', N'Gấu bông tựa lưng Voi nhỏ má hồng', NULL, 40, CAST(0x0D410B00 AS Date), N'Gt')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Photo], [Description], [Discount], [Quantity], [Created], [CateId]) VALUES (5, N'Koala', 280, N'GtKoala.jpg', N'Gấu bông tựa lưng Gấu Koala má hồng 20x30x50', 0, 5, CAST(0x0D410B00 AS Date), N'Gt')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Photo], [Description], [Discount], [Quantity], [Created], [CateId]) VALUES (6, N'Khủng long', 180, N'GbKhunglong.jpg', N'Gấu bông MJ Baby Fat Dinosaur ôm thỏ cute 30cm', NULL, 25, CAST(0x0D410B00 AS Date), N'Gb')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Photo], [Description], [Discount], [Quantity], [Created], [CateId]) VALUES (7, N'Khủng long', 245, N'Gtkhunglong.jpg', N'Gấu bông tựa lưng vuông Khủng long sừng vàng ngủ 32x35', NULL, 24, CAST(0x0D410B00 AS Date), N'Gt')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Photo], [Description], [Discount], [Quantity], [Created], [CateId]) VALUES (8, N'Pink panther', 90, N'GbPinkpanther.jpg', N'Gấu bông Pink panther 20cm', NULL, 42, CAST(0x0D410B00 AS Date), N'Gb')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Photo], [Description], [Discount], [Quantity], [Created], [CateId]) VALUES (9, N'Totoro', 350, N'GbTotoro.jpg', N'Gấu bông Totoro lông xù 60cm', NULL, 20, CAST(0x0D410B00 AS Date), N'Gb')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Photo], [Description], [Discount], [Quantity], [Created], [CateId]) VALUES (10, N'Vô diện', 250, N'GbVodien.jpg', N'Gấu bông Vô diện 60cm', NULL, 40, CAST(0x0D410B00 AS Date), N'Gb')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Photo], [Description], [Discount], [Quantity], [Created], [CateId]) VALUES (11, N'Vịt', 290, N'GbVit.jpg', N'Gấu bông Lalafanfan Cafe Mimi đeo băng đô 30cm', NULL, 9, CAST(0xEF400B00 AS Date), N'Gb')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Photo], [Description], [Discount], [Quantity], [Created], [CateId]) VALUES (12, N'Heo', 245, N'GtHeo.jpg', N'Gấu bông tựa lưng vuông Heo mắt tim 30x30 - Hồng', NULL, 19, CAST(0xEE400B00 AS Date), N'Gt')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Photo], [Description], [Discount], [Quantity], [Created], [CateId]) VALUES (13, N'Carrot', 245, N'GtCarrot.jpg', N'Gấu bông tựa lưng vuông Carrot Cartoon 30x30 - Cà rốt', NULL, 29, CAST(0x6C410B00 AS Date), N'Gt')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Photo], [Description], [Discount], [Quantity], [Created], [CateId]) VALUES (14, N'Thỏ', 280, N'GbTho.jpg', N'Gấu bông Thỏ Amuse cao cấp 45cm', NULL, 27, CAST(0x8C410B00 AS Date), N'Gb')
INSERT [dbo].[Products] ([Id], [Name], [Price], [Photo], [Description], [Discount], [Quantity], [Created], [CateId]) VALUES (15, N'Mèo', 245, N'GtMeo.jpg', N'Gấu bông tựa lưng vuông Mèo xương cá 30x30', NULL, 44, CAST(0x8C410B00 AS Date), N'Gt')
INSERT [dbo].[Status] ([Id], [Name]) VALUES (0, N'Đã hủy')
INSERT [dbo].[Status] ([Id], [Name]) VALUES (1, N'Đang xác nhận')
INSERT [dbo].[Status] ([Id], [Name]) VALUES (2, N'Đang giao hàng')
INSERT [dbo].[Status] ([Id], [Name]) VALUES (3, N'Hoàn tất đơn hàng')
INSERT [dbo].[Users] ([Id], [Name], [Gender], [Username], [Password], [Email], [Phone], [Address], [Created], [UserRole]) VALUES (1, N'NyNy', 0, N'NyNy', N'nyny123', N'lenaahgase.got7@gmail.com', N'0942182077', N'Bình Thạnh, HCM', CAST(0x93400B00 AS Date), N'admin')
INSERT [dbo].[Users] ([Id], [Name], [Gender], [Username], [Password], [Email], [Phone], [Address], [Created], [UserRole]) VALUES (2, N'Gia Nhĩ', 1, N'GiaNhi', N'gianhi', N'lenaahgase.got7@gmail.com', N'0942182077', N'HCM', CAST(0x0D410B00 AS Date), N'client')
INSERT [dbo].[Users] ([Id], [Name], [Gender], [Username], [Password], [Email], [Phone], [Address], [Created], [UserRole]) VALUES (3, N'Nghi Ân', 1, N'NghiAn', N'nghian', N'lenaahgase.got7@gmail.com', N'0942182077', N'HCM', CAST(0x6C410B00 AS Date), N'client')
INSERT [dbo].[Users] ([Id], [Name], [Gender], [Username], [Password], [Email], [Phone], [Address], [Created], [UserRole]) VALUES (4, N'Tể Phạm', 1, N'TePham', N'tepham', N'lenaahgase.got7@gmail.com', N'0942182077', N'Bình Thạnh, HCM', CAST(0x8C410B00 AS Date), N'client')
ALTER TABLE [dbo].[OrderDetails]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetails_Orders] FOREIGN KEY([OrderId])
REFERENCES [dbo].[Orders] ([Id])
GO
ALTER TABLE [dbo].[OrderDetails] CHECK CONSTRAINT [FK_OrderDetails_Orders]
GO
ALTER TABLE [dbo].[OrderDetails]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetails_Products] FOREIGN KEY([ProId])
REFERENCES [dbo].[Products] ([Id])
GO
ALTER TABLE [dbo].[OrderDetails] CHECK CONSTRAINT [FK_OrderDetails_Products]
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK_Orders_Status] FOREIGN KEY([StatusId])
REFERENCES [dbo].[Status] ([Id])
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK_Orders_Status]
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK_Orders_Users] FOREIGN KEY([UserId])
REFERENCES [dbo].[Users] ([Id])
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK_Orders_Users]
GO
ALTER TABLE [dbo].[Products]  WITH CHECK ADD  CONSTRAINT [FK_Products_Category] FOREIGN KEY([CateId])
REFERENCES [dbo].[Category] ([Id])
GO
ALTER TABLE [dbo].[Products] CHECK CONSTRAINT [FK_Products_Category]
GO
